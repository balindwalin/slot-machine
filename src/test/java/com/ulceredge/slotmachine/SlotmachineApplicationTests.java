package com.ulceredge.slotmachine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlotmachineApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void SlotmachineIndexTest() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string(
                        "*** Wellcome to Slot Machine! ***\n\n"
                        + "Use:\n/account -to check your account's balance\n"
                        + "/account/deposit?amount=X -to change the balance (where X ist amount of credits)\n\n"
                        + "/spin -to pull the lever (default stake is 3 credits)\n"
                        + "/spin?increase_stake=X -to increase your stake (where X is the amount of additional credits)\n\n"
                        + "/ -to view this help\n"));
    }

    @Test
    public void SlotmachineAccountTest() throws Exception {
        mockMvc.perform(get("/account")).andExpect(status().isOk())
                .andExpect(content().string("Your account's balance is 0 credits.\n"));

        mockMvc.perform(get("/account/deposit?amount=100")).andExpect(status().isOk())
                .andExpect(content().string("You successfully deposited 100 credits. Now your account's balance is 100 credits.\n"));

        mockMvc.perform(get("/account/deposit?amount=-60")).andExpect(status().isOk())
                .andExpect(content().string("You successfully deposited -60 credits. Now your account's balance is 40 credits.\n"));

        mockMvc.perform(get("/account/deposit?amount=-60")).andExpect(status().isOk())
                .andExpect(content().string("Unacceptable deposit: -60 credits. Your account's balance is 40 credits.\n"));
    }

    @Test
    public void SlotmachineStakeTest() throws Exception {
        mockMvc.perform(get("/account")).andExpect(status().isOk())
                .andExpect(content().string("Your account's balance is 40 credits.\n"));

        mockMvc.perform(get("/spin?increase_stake=38")).andExpect(status().isOk())
                .andExpect(content().string("You have not enough money on your balance to play!\n"));

        mockMvc.perform(get("/spin?increase_stake=-1")).andExpect(status().isOk())
                .andExpect(content().string("Wrong stake value: 2 credits!\n"));
    }

}
