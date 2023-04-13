package com.cops.ntsf.service;

public class ComplaintService {
    /*
    @ Check Validations function
    * */
    public boolean checkValidations(String title, String description) {

        boolean flagTitle = false; // flag = true means title validation is passed
        boolean flagDescription = false; // flag = true means description validation is passed

        /*
        @ Validate title
        * */
        if (title.trim().equals("")) {
            System.out.println("Title is empty");
        } else if (title.trim().length() < 3) {
            System.out.println("Title is too short");
        } else if (title.trim().length() > 50) {
            System.out.println("Title is too long");
        } else {
            System.out.println("Title is valid");
            flagTitle = true;
        }

        /*
        @ Validate description
        * */
        if (description.trim().equals("")) {
            System.out.println("Description is empty");
        } else if (description.trim().length() < 10) {
            System.out.println("Description is too short");
        } else if (description.trim().length() > 500) {
            System.out.println("Description is too long");
        } else {
            System.out.println("Description is valid");
            flagDescription = true;
        }

        // Simplify 'if else'
        boolean flag;
        flag = flagTitle && flagDescription;
        return flagTitle;
    }
}
