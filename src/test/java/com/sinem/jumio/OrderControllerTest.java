package com.sinem.jumio;

import com.google.gson.Gson;
import com.sinem.jumio.controller.CustomerController;
import com.sinem.jumio.dataTransferObject.BookDTO;
import com.sinem.jumio.dataTransferObject.CustomerDTO;
import com.sinem.jumio.dataTransferObject.OrderDTO;
import com.sinem.jumio.dataTransferObject.OrderItemDTO;
import com.sinem.jumio.dataTransferObject.StockDTO;
import com.sinem.jumio.domainValue.OrderStatus;
import com.sinem.jumio.service.customer.ICustomerService;
import com.sinem.jumio.service.order.IOrderService;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class, secure = false)
public class OrderControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICustomerService iCustomerService;


    @Test
    public void retrieveAllOrder() throws Exception
    {

        Mockito.when(
            iCustomerService.getAllOrdersOfCustomer(new Long(1))).thenReturn(mockOrderDTOs());

        RequestBuilder requestBuilder = get(
            "/v1/customer").accept(
            MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"id\":1,\"orderStatus\":\"CREATED\",\"customerDTO\":{\"id\":1,\"username\":\"sertac\",\"password\":\"eksi\",\"orderDTOList\":[]},\"orderItemDTOSet\":[]},{\"id\":1,\"orderStatus\":\"CREATED\",\"customerDTO\":{\"id\":1,\"username\":\"sertac\",\"password\":\"eksi\",\"orderDTOList\":[]},\"orderItemDTOSet\":[]},{\"id\":1,\"orderStatus\":\"CREATED\",\"customerDTO\":{\"id\":1,\"username\":\"sertac\",\"password\":\"eksi\",\"orderDTOList\":[]},\"orderItemDTOSet\":[]}]";
        expected = expected.replaceAll("\n", "\\n");
        JSONArray js = new JSONArray(expected);

        JSONAssert.assertEquals(js.toString(), result.getResponse()
            .getContentAsString(), true);
    }


    @Test
    public void sendOrderTest() throws Exception
    {
        BookDTO.BookDTOBuilder bookDTOBuilder = BookDTO.newBuilder()
            .setId(new Long(10))
            .setTitle("Lord of the rings")
            .setIsbn("1231332424");

        BookDTO bookDTO = bookDTOBuilder.createBookDTO();

        StockDTO.StockDTOBuilder stockDTOBuilder = StockDTO.newBuilder()
            .setId(new Long(4))
            .setBookDTO(bookDTO)
            .setNumberInStock(100);
        StockDTO stockDTO = stockDTOBuilder.createStockDTO();

        OrderItemDTO.OrderItemDTOBuilder orderItemDTOBuilder = OrderItemDTO.newBuilder()
            .setId(new Long(9))
            .setQuantity(12)
            .setbookDTO(bookDTOBuilder.createBookDTO());

        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        orderItemDTOList.add(orderItemDTOBuilder.createOrderItemDTO());

        CustomerDTO.CustomerDTOBuilder customerDTOBuilder = CustomerDTO.newBuilder()
            .setId(new Long(1))
            .setUsername("sinem")
            .setPassword("kalay");

        OrderDTO.OrderDTOBuilder orderDTOBuilder = OrderDTO.newBuilder()
            .setOrderStatus(OrderStatus.CREATED)
            .setOrderItemDTO(orderItemDTOList)
            .setCustomerDTO(customerDTOBuilder.createCustomerDTO());

        OrderDTO orderDTO = orderDTOBuilder.createOrderDTO();

        Gson gson = new Gson();
        String orderDTOJson = gson.toJson(orderDTO);
        System.out.println(orderDTOJson);

        Mockito.doAnswer((Answer<OrderDTO>) invocation -> null).when(iCustomerService).sendOrder(new Long(1),new Long(1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/customer/").accept(MediaType.APPLICATION_JSON).content(orderDTOJson)
            .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void testGetRestController() throws Exception
    {
        Mockito.when(iCustomerService.getAllOrdersOfCustomer(new Long(1))).thenReturn(mockOrderDTOs());
        mockMvc.perform(get("/v1/orders"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$", Matchers.hasSize(3)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].username", is("sertac")))
            .andExpect(jsonPath("$[0].password", is("eksi")))

            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].username", is("sertac")))
            .andExpect(jsonPath("$[1].password", is("eksi")))

            .andExpect(jsonPath("$[2].id", is(3)))
            .andExpect(jsonPath("$[2].username", is("sertac")))
            .andExpect(jsonPath("$[2].password", is("eksi")))
        ;
        Mockito.verify(iCustomerService, Mockito.times(1)).getAllOrdersOfCustomer(new Long(1));
        Mockito.verifyNoMoreInteractions(iCustomerService);
    }


    @Test
    public void createOrderTest() throws Exception
    {

        String orderJson = "{\"orderStatus\":\"CREATED\",\"customerDTO\":{\"id\": 1,\"username\": \"sinem\",\"password\": \"kalay\",\"orderDTOList\":[]},\"orderItemDTOSet\":[{\"id\":1,  \"bookDTO\":{\"id\":1,  \"isbn\":\"12sddf3q3464545\",\"authorDTO\":{\"id\":1,\"firstName\":\"jack\",  \"lastName\":\"kerouac\"}},\"quantity\":2},{\"id\":2,\"bookDTO\":{\"id\":1,  \"isbn\":\"12sddf3q3464545\",\"authorDTO\":{\"id\":1,\"firstName\":\"jack\",\"lastName\":\"kerouac\"}},\"quantity\":2}]}";
        Mockito.doAnswer((Answer<OrderDTO>) invocation -> null).when(iCustomerService).createOrder(Mockito.any(OrderDTO.class), new Long(1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/orders").accept(MediaType.APPLICATION_JSON).content(orderJson)
            .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    private List<OrderDTO> mockOrderDTOs()
    {
        List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
        CustomerDTO customerDTO = new CustomerDTO(new Long(1), "sertac", "eksi", orderDTOList);
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();

        OrderDTO mockOrder = new OrderDTO(new Long(1), OrderStatus.CREATED, orderItemDTOList);

        orderDTOList.add(mockOrder);
        orderDTOList.add(mockOrder);
        orderDTOList.add(mockOrder);

        return orderDTOList;

    }
}
