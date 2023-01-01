package com.fci.fawrysystem.controllers.discount;

import com.fci.fawrysystem.models.MySystem;
import com.fci.fawrysystem.models.account.Admin;
import com.fci.fawrysystem.models.account.IAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DiscountController {
    CostManager discountCalculator;
    MySystem system;

    DiscountController() {
        discountCalculator = DiscountCalculator.getInstance();
        system = MySystem.getInstance();
    }

    @GetMapping(value = "/discount/overall")
    public String overAllDiscount(@RequestParam(value = "userName") String userName) {

        IAccount account = system.getAccount(userName);

        if(!(account instanceof Admin)) {
            return "admin needed :(";
        }

        discountCalculator = DiscountCalculator.overallDiscount();
        return "Discount added :)";
    }

    @GetMapping(value = "/discount/specific")
    public String specificDiscount(@RequestBody Map<String, String> payload, @RequestParam(value = "userName") String userName) {

        IAccount account = system.getAccount(userName);

        if(!(account instanceof Admin)) {
            return "admin needed :(";
        }

        String serviceName = payload.get("serviceName");
        discountCalculator = DiscountCalculator.specificDiscount(serviceName.toLowerCase());
        return "Discount added to " + serviceName + " ;)";
    }
}
