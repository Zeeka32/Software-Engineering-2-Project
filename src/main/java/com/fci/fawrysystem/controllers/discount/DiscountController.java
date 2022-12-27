package com.fci.fawrysystem.controllers.discount;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DiscountController {
    CostManager discountCalculator;

    DiscountController() {
        discountCalculator = DiscountCalculator.getInstance();
    }

    @GetMapping(value = "/discount/overall")
    public String overAllDiscount() {
        discountCalculator = DiscountCalculator.overallDiscount();
        return "Discount added :)";
    }

    @GetMapping(value = "/discount/specific")
    public String specificDiscount(@RequestBody Map<String, String> payload) {
        String serviceName = payload.get("serviceName");
        discountCalculator = DiscountCalculator.specificDiscount(serviceName);
        return "Discount added to " + serviceName + " ;)";
    }
}
