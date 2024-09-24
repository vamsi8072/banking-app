import java.util.*;

public class BankingApp {

    private class BankDetails {
        private String accno;
        private String name;
        private String fatherName;
        private String motherName;
        private String bloodGroup;
        private int age;
        private String dob;
        private String gender;
        private String address;
        private String pincode;
        private String mobile;
        private String email;
        private String aadhar;
        private String pan;
        private String acc_type;
        private String modeOfOperation;
        private List<String> needs = new ArrayList<>();
        private String status;
        private String occupation;
        private boolean kyc;
        private long balance;

        private String generateAccountNumber() {
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                sb.append(random.nextInt(10));
            }
            return sb.toString();
        }

        private int generateOtp() {
            Random random = new Random();
            return 1000 + random.nextInt(9000);
        }

        public boolean verifyMobile(String mobile) {
            if (mobile.matches("\\d{10}")) {
                int otp = generateOtp();
                System.out.println("An OTP has been sent to your mobile number: " + mobile);
                System.out.println("Your OTP is: " + otp); // Simulate sending OTP
                System.out.print("Enter the OTP: ");
                Scanner sc = new Scanner(System.in);
                int inputOtp = sc.nextInt();
                sc.nextLine(); // Consume newline
                return inputOtp == otp;
            }
            return false;
        }

        public void openAccount() {
            Scanner sc = new Scanner(System.in);
            accno = generateAccountNumber();
            System.out.println("Generated Account No: " + accno);
            System.out.print("Enter Name: ");
            name = sc.nextLine();
            System.out.print("Enter Father's Name: ");
            fatherName = sc.nextLine();
            System.out.print("Enter Mother's Name: ");
            motherName = sc.nextLine();
            System.out.print("Enter Blood Group: ");
            bloodGroup = sc.nextLine();
            do {
                System.out.print("Enter Age (Must be above 18): ");
                age = sc.nextInt();
                sc.nextLine();
                if (age < 18) {
                    System.out.println("You must be at least 18 years old to open an account.");
                }
            } while (age < 18);
            System.out.print("Enter Date of Birth (DOB) [DD/MM/YYYY]: ");
            dob = sc.nextLine();
            System.out.print("Enter Gender: ");
            gender = sc.nextLine();
            System.out.print("Enter Address: ");
            address = sc.nextLine();
            System.out.print("Enter Pincode: ");
            pincode = sc.nextLine();
            do {
                System.out.print("Enter Mobile No: ");
                mobile = sc.nextLine();
                if (!verifyMobile(mobile)) {
                    System.out.println("Invalid mobile number or OTP. Please try again.");
                }
            } while (!verifyMobile(mobile));
            System.out.print("Enter Email: ");
            email = sc.nextLine();
            System.out.print("Enter Aadhaar Number (12 digits): ");
            aadhar = sc.nextLine();
            while (aadhar.length() != 12 || !aadhar.matches("\\d+")) {
                System.out.print("Invalid Aadhaar Number. Enter a valid 12-digit Aadhaar Number: ");
                aadhar = sc.nextLine();
            }
            System.out.print("Enter PAN Number (10 characters, format: AAAAA9999A): ");
            pan = sc.nextLine();
            while (!pan.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
                System.out.print("Invalid PAN Number. Enter a valid PAN Number (format: AAAAA9999A): ");
                pan = sc.nextLine();
            }
            System.out.print("Enter Account Type (Savings/Current): ");
            acc_type = sc.nextLine();
            System.out.print("Enter Mode of Operation (Single/Family): ");
            modeOfOperation = sc.nextLine();
            System.out.print("Do you need a Cheque Book? (yes/no): ");
            if (sc.nextLine().equalsIgnoreCase("yes")) needs.add("Cheque Book");
            System.out.print("Do you need an ATM Card? (yes/no): ");
            if (sc.nextLine().equalsIgnoreCase("yes")) needs.add("ATM Card");
            System.out.print("Do you need a Debit Card? (yes/no): ");
            if (sc.nextLine().equalsIgnoreCase("yes")) needs.add("Debit Card");
            System.out.print("Do you need a Credit Card? (yes/no): ");
            if (sc.nextLine().equalsIgnoreCase("yes")) needs.add("Credit Card");
            System.out.print("Enter Marital Status (Married/Single/Others): ");
            status = sc.nextLine();
            System.out.print("Enter Occupation: ");
            occupation = sc.nextLine();
            System.out.print("Has KYC been done? (true/false): ");
            kyc = sc.nextBoolean();
            sc.nextLine(); // Consume newline

            System.out.print("Enter Initial Balance: ");
            balance = sc.nextLong();
            System.out.println("Successfully Opened the Account");
        }

        public void showAccount() {
            System.out.println("Account No.: " + accno);
            System.out.println("Name: " + name);
            System.out.println("Father's Name: " + fatherName);
            System.out.println("DOB: " + dob);
            System.out.println("Mobile No.: " + mobile);
            System.out.println("Account Type: " + acc_type);
            System.out.println("Balance: " + balance);
        }

        public void showBalance() {
            System.out.println("Balance: " + balance);
        }

        public void deposit() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the amount you want to deposit: ");
            long amt = sc.nextLong();
            balance += amt;
            System.out.println("Successfully Deposited.");
        }

        public void withdrawal() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the amount you want to withdraw: ");
            long amt = sc.nextLong();
            if (balance >= amt) {
                balance -= amt;
                System.out.println("Successfully Withdrawn.");
            } else {
                System.out.println("Insufficient Balance.");
            }
        }

        public boolean search(String ac_no) {
            return ac_no.equals(accno);
        }

        public boolean verifyOtpForAccount(String ac_no) {
            if (search(ac_no)) {
                return verifyMobile(mobile);
            }
            return false;
        }

        public boolean applyForLoan() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Loan Amount: ");
            long loanAmount = sc.nextLong();
            sc.nextLine(); // Consume newline
            int otp = generateOtp();
            System.out.println("An OTP has been sent to your mobile number: " + mobile);
            System.out.println("Your OTP is: " + otp); // Simulate sending OTP
            System.out.print("Enter the OTP to approve loan: ");
            int inputOtp = sc.nextInt();
            sc.nextLine(); // Consume newline
            if (inputOtp == otp) {
                balance += loanAmount; // Add loan amount to the balance
                System.out.println("Loan approved successfully. The loan amount has been added to your balance.");
                return true;
            } else {
                System.out.println("Loan approval failed due to incorrect OTP.");
                return false;
            }
        }
    }

    private Map<String, BankDetails> accounts = new HashMap<>();

    private void clientMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter Account Number: ");
            String accno = sc.nextLine();
            BankDetails bd = accounts.get(accno);
            if (bd != null && bd.verifyOtpForAccount(accno)) {
                System.out.println("Welcome to the Bank");
                System.out.println("1. Show Account Details");
                System.out.println("2. Show Balance");
                System.out.println("3. Deposit");
                System.out.println("4. Withdraw");
                System.out.println("5. Back to Main Menu");
                System.out.println("6. Exit");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        bd.showAccount();
                        break;
                    case 2:
                        bd.showBalance();
                        break;
                    case 3:
                        bd.deposit();
                        break;
                    case 4:
                        bd.withdrawal();
                        break;
                    case 5:
                        return; // Go back to the main menu
                    case 6:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            } else {
                System.out.println("Account not found or OTP verification failed.");
            }
        }
    }

    private void staffMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome Staff");
            System.out.println("1. Open New Account");
            System.out.println("2. Show Account Details");
            System.out.println("3. Show Balance");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Apply for Loan");
            System.out.println("7. Back to Main Menu");
            System.out.println("8. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    BankDetails newAccount = new BankDetails();
                    newAccount.openAccount();
                    accounts.put(newAccount.accno, newAccount);
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    String accno = sc.nextLine();
                    BankDetails bd = accounts.get(accno);
                    if (bd != null) {
                        bd.showAccount();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accno = sc.nextLine();
                    bd = accounts.get(accno);
                    if (bd != null) {
                        bd.showBalance();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accno = sc.nextLine();
                    bd = accounts.get(accno);
                    if (bd != null) {
                        bd.deposit();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    accno = sc.nextLine();
                    bd = accounts.get(accno);
                    if (bd != null) {
                        bd.withdrawal();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter Account Number: ");
                    accno = sc.nextLine();
                    bd = accounts.get(accno);
                    if (bd != null) {
                        if (bd.applyForLoan()) {
                            System.out.println("Loan approved successfully.");
                        } else {
                            System.out.println("Loan approval failed due to incorrect OTP.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 7:
                    return; // Go back to the main menu
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private boolean staffLogin() {
        String staffUsername = "vamsi@gmail.com"; // Hardcoded username for demonstration
        String staffPassword = "********"; // Hardcoded password for demonstration
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Staff Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Staff Password: ");
        String password = sc.nextLine();
        return username.equals(staffUsername) && password.equals(staffPassword);
    }

    public static void main(String[] args) {
        BankingApp app = new BankingApp();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Bank");
            System.out.println("1. Staff Login");
            System.out.println("2. Client Access");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    if (app.staffLogin()) {
                        app.staffMenu();
                    } else {
                        System.out.println("Invalid login credentials.");
                    }
                    break;
                case 2:
                    app.clientMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}
