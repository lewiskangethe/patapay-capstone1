PataPay

The name "PataPay" is inspired by my country of origin, "Pata" means "to get" or "to receive," while "Pay" refers to giving money. This combination reflects the purpose of the application: helping you manage your finances efficiently. In a world of recurring payments, subscriptions, rent, and bills, PataPay empowers users to record, view, and analyze transactions in a structured way to help save more and spend less.

PataPay is an application that provides a clear and organized view of your financial activity. Whether you are logging daily expenses or reviewing year-to-date totals, PataPay gives you complete control of your money directly from the terminal.

Features

Add Deposits & Payments
Record financial transactions quickly from the home menu. Payments are stored as negative amounts to ensure accurate totals.

Transaction Ledger
View all transactions, or filter by deposits or payments. Transactions are displayed with the newest entries first. All data is stored locally in transactions.csv using a simple | delimiter.

Detailed Reports
Generate reports to gain insights into your finances, including:

Month-to-Date

Previous Month

Year-to-Date

Previous Year

Vendor Search (case-insensitive)

Search by Vendor
Quickly locate all transactions associated with a specific vendor.

Local Data Storage
All transactions are stored locally in a portable .csv file.

Project Structure

PataPayTransaction.java → Defines the structure and properties of a transaction.

PataPayApplication.java → Handles user interaction, menu navigation, file input/output, and report generation.

Example Usage

Adding a Deposit

**********
HOME SCREEN
**********
D) Make Deposit
P) Make Payment (Debit)
L) Ledger
X) Exit
**********
Enter choice: D

Enter date (yyyy-MM-dd): 2025-10-17
Enter time (HH:mm:ss): 09:15:00
Enter description: Paycheck
Enter vendor: Employer Inc
Enter amount: 2500.00

Deposit recorded successfully!

Author

Created by Lewis Kangethe
