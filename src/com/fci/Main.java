package com.fci;

import com.fci.Entities.*;
import com.fci.Payment.CashOnDelivery;
import com.fci.Payment.Discounts.*;
import com.fci.Payment.IPaymentMethod;
import com.fci.Payment.PayWithCreditCard;
import com.fci.Payment.PayWithWallet;
import com.fci.Services.Factory.*;
import com.fci.Services.ServiceProviders.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(java.lang.String[] args) {

        MySystem system = new MySystem();
        Scanner scanner = new Scanner(System.in);
        CostManager costManager = new DiscountCalculator();

        ServiceFactory myDonationsFactory = new ConcreteDonationsFactory();
        ServiceFactory myMobileFactory = new ConcreteMobileFactory();
        ServiceFactory myInternetFactory = new ConcreteInternetFactory();
        ServiceFactory myLandlineFactory = new ConcreteLandlineFactory();

        HashMap<String, Service> MobileRechargeServices = new HashMap<>();
        HashMap<String, Service> InternetPaymentServices = new HashMap<>();
        HashMap<String, Service> LandlineServices = new HashMap<>();
        HashMap<String, Service> donationServiceProviders = new HashMap<>();

        MobileRechargeServices.put("VodafoneMobile", myMobileFactory.create("VodafoneMobile"));
        MobileRechargeServices.put("EtisalatMobile", myMobileFactory.create("EtisalatMobile"));
        MobileRechargeServices.put("OrangeMobile", myMobileFactory.create("OrangeMobile"));
        MobileRechargeServices.put("WEMobile", myMobileFactory.create("WEMobile"));

        InternetPaymentServices.put("VodafoneInternet", myInternetFactory.create("VodafoneInternet"));
        InternetPaymentServices.put("EtisalatInternet", myInternetFactory.create("EtisalatInternet"));
        InternetPaymentServices.put("OrangeInternet", myInternetFactory.create("OrangeInternet"));
        InternetPaymentServices.put("WEInternet", myInternetFactory.create("WEInternet"));

        LandlineServices.put("QuarterReceipt", myLandlineFactory.create("QuarterReceipt"));
        LandlineServices.put("MonthlyReceipt", myLandlineFactory.create("MonthlyReceipt"));

        donationServiceProviders.put("School", myDonationsFactory.create("School"));
        donationServiceProviders.put("NGO", myDonationsFactory.create("NGO"));
        donationServiceProviders.put("Cancer", myDonationsFactory.create("CancerHospital"));

        int option = 1;
        while(option != 0) {
            System.out.print("1- SignUp\n");
            System.out.print("2- SignIn\n");
            System.out.print(">>>> ");
            option = scanner.nextInt();

            if(option == 1) {
                IAccount newAccount;
                java.lang.String userName, email, password;
                System.out.print("Enter your username: ");
                userName = scanner.next();
                System.out.print("Enter your email: ");
                email = scanner.next();
                System.out.print("Enter your password: ");
                password = scanner.next();

                java.lang.String type;
                System.out.print("Account Type: ");
                type = scanner.next();

                if(type.equalsIgnoreCase("admin")) {
                    newAccount = new Admin(userName, email, password);
                }else{
                    newAccount = new User(userName, email, password);
                }

                if(system.signUp(newAccount)){
                    System.out.println("Successfully Signed Up!");
                }else {
                    System.out.println("Failed to Sign Up, try again");
                }
            }
            else if(option == 2) {
                java.lang.String email, password;
                System.out.print("Enter your email: ");
                email = scanner.next();
                System.out.print("Enter your password: ");
                password = scanner.next();


                if(system.signIn(email, password)){
                    System.out.println("Successfully Signed In!");
                }else {
                    System.out.println("Failed to Sign In, try again");
                }

            }

            int serviceOption = -1;
            if(system.getActiveUser() != null) {
                while(serviceOption != 0) {
                    System.out.print("Which Service do you want to do ?\n");
                    System.out.print("1-Mobile Recharge Services\n");
                    System.out.print("2-Internet Payment Services\n");
                    System.out.print("3-Landline Services\n");
                    System.out.print("4-Donation Services\n");
                    System.out.print("5-Add balance to CreditCard\n");
                    System.out.print("6-Transfer funds to wallet\n");
                    System.out.print("7-Ask for a refund\n");
                    System.out.print("8-Show Account Funds\n");

                    if(system.getActiveUser() instanceof Admin) {
                        System.out.print("9-Add Discount\n");
                        System.out.print("10-Show all transactions\n");
                        System.out.print("11-Check refund Requests\n");
                    }

                    System.out.print("0-To Sign Out\n");
                    System.out.print(">>>> ");
                    serviceOption = scanner.nextInt();

                    int providerOption = 0;
                    if(serviceOption == 1) {
                        while(providerOption != 5) {
                            System.out.print("Which provider do you want to use ?\n");
                            System.out.print("1-Vodafone Mobile\n");
                            System.out.print("2-Etisalat Mobile\n");
                            System.out.print("3-Orange Mobile\n");
                            System.out.print("4-WE Mobile\n");
                            System.out.print("5-Go Back\n");
                            System.out.print(">>>> ");
                            providerOption = scanner.nextInt();

                            if(providerOption == 1) {
                                double price = MobileRechargeServices.get("VodafoneMobile").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Mobile");

                                providerOption = 5;
                            }
                            else if(providerOption == 2) {
                                double price = MobileRechargeServices.get("EtisalatMobile").serviceForm(system.getActiveUser());

                                calculatePayment(system, scanner, costManager, price, "Mobile");

                                providerOption = 5;
                            }
                            else if(providerOption == 3) {
                                double price = MobileRechargeServices.get("OrangeMobile").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Mobile");
                                providerOption = 5;
                            }
                            else if(providerOption == 4) {
                                double price = MobileRechargeServices.get("WEMobile").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Mobile");
                                providerOption = 5;
                            }

                        }
                    }
                    else if(serviceOption == 2) {
                        while(providerOption != 5) {
                            System.out.print("Which provider do you want to use ?\n");
                            System.out.print("1-Vodafone Internet\n");
                            System.out.print("2-Etisalat Internet\n");
                            System.out.print("3-Orange Internet\n");
                            System.out.print("4-WE Internet\n");
                            System.out.print("5-Go back\n");
                            System.out.print(">>>> ");
                            providerOption = scanner.nextInt();

                            if(providerOption == 1) {
                                double price = InternetPaymentServices.get("VodafoneInternet").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Internet");

                                providerOption = 5;
                            }else if(providerOption == 2) {
                                double price = InternetPaymentServices.get("EtisalatInternet").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Internet");
                                providerOption = 5;
                            }else if(providerOption == 3) {
                                double price = InternetPaymentServices.get("OrangeInternet").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Internet");
                                providerOption = 5;
                            }else if(providerOption == 4) {
                                double price = InternetPaymentServices.get("WEInternet").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Internet");
                                providerOption = 5;
                            }
                        }
                    }
                    else if(serviceOption == 3) {
                        while(providerOption != 3) {
                            System.out.print("Which Receipt do you want to pay ?\n");
                            System.out.print("1-Quarter Receipt\n");
                            System.out.print("2-Monthly Receipt\n");
                            System.out.print("3-Go back\n");
                            System.out.print(">>>> ");
                            providerOption = scanner.nextInt();

                            if(providerOption == 1) {
                                double price = LandlineServices.get("QuarterReceipt").serviceForm(system.getActiveUser());
                                calculatePaymentLandline(system, scanner, costManager, price, "Landline");
                                providerOption = 3;
                            }else if(providerOption == 2) {
                                double price = LandlineServices.get("MonthlyReceipt").serviceForm(system.getActiveUser());
                                calculatePaymentLandline(system, scanner, costManager, price, "Landline");
                                providerOption = 3;
                            }

                        }
                    }
                    else if(serviceOption == 4) {
                        while(providerOption != 4) {
                            System.out.print("Which provider do you want to donate to ?\n");
                            System.out.print("1-Schools\n");
                            System.out.print("2-Cancer Hospitals\n");
                            System.out.print("3-Non-Profit Organizations\n");
                            System.out.print("4-Go Back\n");
                            System.out.print(">>>> ");
                            providerOption = scanner.nextInt();

                            if(providerOption == 1) {
                                double price = donationServiceProviders.get("School").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Donation");
                                providerOption = 4;

                            }else if(providerOption == 2) {
                                double price = donationServiceProviders.get("Cancer").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Donation");
                                providerOption = 4;
                            }else if(providerOption == 3) {
                                double price = donationServiceProviders.get("NGO").serviceForm(system.getActiveUser());
                                calculatePayment(system, scanner, costManager, price, "Donation");
                                providerOption = 4;
                            }
                        }
                    }
                    else if(serviceOption == 5) {
                        System.out.print("How much funds do you want to add to your credit card: ");
                        double amount = scanner.nextDouble();
                        system.getActiveUser().getCard().receive(amount);
                        System.out.println("Done!");
                    }
                    else if(serviceOption == 6) {
                        System.out.print("how much funds do you want to transfer to your wallet: ");
                        double amount = scanner.nextDouble();

                        if(system.getActiveUser().getCard().pay(amount)) {
                            system.getActiveUser().getWallet().receive(amount);
                            Transaction transaction = new Transaction(system.getActiveUser(), "Add to Wallet Transaction", "Bank System", amount);
                            system.getActiveUser().getAccountHistory().addTransaction(transaction);
                            system.getSystemHistory().addTransaction(transaction);
                            System.out.println("Done!");
                        }else {
                            System.out.println("Failed");
                        }
                    }
                    else if(serviceOption == 7) {
                        int IDofRefund;
                        while(true) {
                            System.out.print("Which Transaction do you want to refund ?\n");
                            History h = system.getActiveUser().getAccountHistory();
                            System.out.println(h.listTransactions());
                            System.out.print("0-Go Back\n");
                            System.out.print(">>>> ");

                            IDofRefund = scanner.nextInt();
                            Transaction T = system.getActiveUser().getAccountHistory().getTransactionHistory().get(IDofRefund);

                            if(IDofRefund == 0) {
                                break;
                            }else if(T != null && !Objects.equals(T.getType(), "Cash On Delivery")) {
                                Transaction transaction = new Transaction(system.getActiveUser(), "Refund Transaction", T.getService(), T.getAmount());

                                system.getRefundRequests().addTransaction(transaction);

                                if(system.getActiveUser() instanceof Admin) {
                                    System.out.println("NOTICE : a new refund request has been Issued");
                                }

                                System.out.println("Admin was Notified of the request");

                                break;
                            }else {
                                System.out.println("Invalid transaction");
                            }
                        }

                    }
                    else if(serviceOption == 8) {
                        System.out.println(system.getActiveUser().getCard().toString() + " " + system.getActiveUser().getWallet().toString());
                    }
                    else if(serviceOption == 9 && system.getActiveUser() instanceof Admin) {
                        while(providerOption != 3) {
                            System.out.print("1-Add overall Discount\n");
                            System.out.print("2-Add Specific Discount\n");
                            System.out.print("3-Go Back\n");
                            System.out.print(">>>> ");
                            providerOption = scanner.nextInt();

                            int discountOption = -1;
                            if(providerOption == 1) {
                                costManager = new Overall(costManager);
                                providerOption = 3;
                            }else if(providerOption == 2) {
                                while(discountOption != 0) {
                                    System.out.println("which service you want to add discount to ?");
                                    System.out.println("1-Mobile Recharge");
                                    System.out.println("2-Internet");
                                    System.out.println("3-Landline");
                                    System.out.println("4-Donations");
                                    System.out.println("5-Go Back");
                                    System.out.print(">>>> ");
                                    discountOption = scanner.nextInt();

                                    if(discountOption == 1) {
                                        costManager = new Specific(costManager, "Mobile");
                                        discountOption = 0;
                                        providerOption = 3;
                                    }else if(discountOption == 2) {
                                        costManager = new Specific(costManager, "Internet");
                                        discountOption = 0;
                                        providerOption = 3;
                                    }else if(discountOption == 3) {
                                        costManager = new Specific(costManager, "Landline");
                                        discountOption = 0;
                                        providerOption = 3;
                                    }else if(discountOption == 4) {
                                        costManager = new Specific(costManager, "Donation");
                                        discountOption = 0;
                                        providerOption = 3;
                                    }else if(discountOption == 5) {
                                        discountOption = 0;
                                        providerOption = 3;
                                    }
                                }
                            }
                        }
                    }
                    else if(serviceOption == 10 && system.getActiveUser() instanceof Admin) {
                        History h = system.getSystemHistory();
                        System.out.println(h.listTransactions());
                    }
                    else if(serviceOption == 11 && system.getActiveUser() instanceof Admin) {
                        int IDofRefund;
                        while(true) {
                            System.out.print("Which Refund do you want to approve or deny ?\n");
                            History h = system.getRefundRequests();
                            System.out.println(h.listTransactions());
                            System.out.print("0-Go Back\n");
                            System.out.print(">>>> ");

                            IDofRefund = scanner.nextInt();
                            Transaction T = system.getRefundRequests().getTransactionHistory().get(IDofRefund);
                            int approve;
                            if(IDofRefund == 0) {
                                break;
                            }else if(T != null) {

                                System.out.println("Approve this refund or deny it ? (1 for approve, 2 for deny)");
                                approve = scanner.nextInt();

                                if(approve == 1) {
                                    Transaction transaction = new Transaction(T.getUser(), "Refund Transaction", T.getService(), T.getAmount());
                                    T.getUser().getCard().receive(T.getAmount());
                                    system.getSystemHistory().addTransaction(transaction);
                                    System.out.println("Amount Refunded Successfully and this user has been notified!");
                                    system.getRefundRequests().getTransactionHistory().remove(IDofRefund);
                                }else if(approve == 2) {
                                    System.out.println("Request has been denied, user has been informed");
                                    system.getRefundRequests().getTransactionHistory().remove(IDofRefund);
                                }
                                break;
                            }else {
                                System.out.println("Invalid transaction");
                            }
                        }
                    }
                    else if(serviceOption == 0) {
                        system.signOut();
                    }
                }
            }
        }
    }

    private static void calculatePayment(MySystem system, Scanner scanner, CostManager costManager, double price, String service) {
        IPaymentMethod paymentMethod;
        price = costManager.calculateDiscount(system.getActiveUser(), system, price, service);

        System.out.println("How would you like to pay for this service ?");
        int paymentOption = -1;
        while(paymentOption != 1 && paymentOption != 2) {
            System.out.println("1-Credit Card");
            System.out.println("2-Wallet");
            System.out.print(">>>>");
            paymentOption = scanner.nextInt();
        }

        if(paymentOption == 1) {
            paymentMethod = new PayWithCreditCard();
        }else {
            paymentMethod = new PayWithWallet();
        }

        paymentMethod.pay(system.getActiveUser(), system, price, service);
    }
    private static void calculatePaymentLandline(MySystem system, Scanner scanner, CostManager costManager, double price, String service) {
        IPaymentMethod paymentMethod;
        price = costManager.calculateDiscount(system.getActiveUser(), system, price, service);

        System.out.println("How would you like to pay for this service ?");
        int paymentOption = -1;
        while(paymentOption != 1 && paymentOption != 2 && paymentOption != 3) {
            System.out.println("1-Credit Card");
            System.out.println("2-Wallet");
            System.out.println("3-Cash On Delivery");
            System.out.print(">>>>");
            paymentOption = scanner.nextInt();
        }

        if(paymentOption == 1) {
            paymentMethod = new PayWithCreditCard();
        }else if(paymentOption == 2){
            paymentMethod = new PayWithWallet();
        }else {
            paymentMethod = new CashOnDelivery();
        }

        paymentMethod.pay(system.getActiveUser(), system, price, service);
    }
}
