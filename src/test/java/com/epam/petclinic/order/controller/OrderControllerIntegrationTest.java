package com.epam.petclinic.order.controller;

import com.epam.petclinic.order.api.ClinicApi;
import com.epam.petclinic.order.domain.Order;
import com.epam.petclinic.order.repository.IOrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class OrderControllerIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderController orderController;
    private ClinicApi clinicApi;
    private MockMvc mockMvc;

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");
    private RestDocumentationResultHandler document;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(restDocumentation))
                .build();
        this.clinicApi = EasyMock.createMock(ClinicApi.class);
        this.orderController.setClinicApi(clinicApi);
    }

    @Test
    public void testGetOrders() throws Exception {
        Order order = initOrder();
        order.setId(UUID.randomUUID());
        orderRepository.save(order);
        document = MockMvcRestDocumentation.document("order-list", responseFields(
                fieldWithPath("[].id").description("The orders' ID"),
                fieldWithPath("[].name").description("The orders' name"),
                fieldWithPath("[].email").description("The orders' email"),
                fieldWithPath("[].address").description("The orders' address"),
                fieldWithPath("[].animalId").description("The orders' animal ID"),
                fieldWithPath("[].serviceIds[]").description("The orders' service IDs")
                )
        );
        mockMvc.perform(get("/orders").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document)
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singleton(order))));
    }

    @Test
    public void testSaveOrder() throws Exception {
        Order order = initOrder();
        ConstrainedFields fields = new ConstrainedFields(Order.class);
        document = MockMvcRestDocumentation.document("order-create", requestFields(
                fields.withPath("id").description("The nullable ID of order"),
                fields.withPath("name").description("The name of order's customer"),
                fields.withPath("email").description("The email of order's customer"),
                fields.withPath("address").description("The address of order's customer"),
                fields.withPath("animalId").description("The order's animal ID"),
                fields.withPath("serviceIds").description("The service ID list for order"))
        );
        expect(clinicApi.getClinicIdByAnimalIdAndServiceIds(order.getAnimalId(), order.getServiceIds()))
                .andReturn(Collections.EMPTY_LIST).once();
        replay(clinicApi);
        assertTrue(CollectionUtils.isEmpty(orderRepository.findAll()));
        mockMvc.perform(post("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andDo(document)
                .andExpect(status().isCreated());
        List<Order> savedOrders = orderRepository.findAll();
        assertNotNull(savedOrders);
        assertEquals(1, savedOrders.size());
        savedOrders.get(0).setId(null);
        assertEquals(order, savedOrders.get(0));
        verify(clinicApi);
    }

    private Order initOrder() {
        Order order = new Order();
        order.setName("test");
        order.setEmail("test@test.test");
        order.setAddress("test");
        order.setAnimalId(UUID.randomUUID());
        order.setServiceIds(Lists.newArrayList(UUID.randomUUID(), UUID.randomUUID()));
        return order;
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }

}
