//package com.cops.ntsf.controller;
//
//
//
//
//
//import java.util.HashMap;
//        import java.util.Map;
//
//        import com.stripe.Stripe;
//        import com.stripe.exception.CardException;
//        import com.stripe.exception.StripeException;
//        import com.stripe.model.Charge;
//
//public class PaymentGateway {
//
//    public static void main(String[] args) {
//        String apiKey = "your_api_key_here"; // your secret API key from Stripe
//        Stripe.apiKey = apiKey;
//
//        try {
//            Map<String, Object> chargeParams = new HashMap<String, Object>();
//            chargeParams.put("amount", 2000); // amount in cents
//            chargeParams.put("currency", "usd"); // currency code
//            chargeParams.put("description", "Test Payment");
//            chargeParams.put("source", "tok_visa"); // obtained with Stripe.js
//
//            Charge charge = Charge.create(chargeParams);
//            System.out.println("Payment was successful with Charge ID: " + charge.getId());
//        } catch (CardException e) {
//            System.out.println("Card was declined: " + e.getMessage());
//        } catch (StripeException e) {
//            System.out.println("An error occurred: " + e.getMessage());
//        }
//    }
//}
