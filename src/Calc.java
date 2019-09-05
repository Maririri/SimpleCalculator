/**
 * Created by Mari on 08/05/15.
 */

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Calc implements ActionListener {

    JFrame f;

    private JLabel l1, l2, l3, l4, l5, l6;
    private JTextField initPayment;
    private JTextField loan;
    private JTextField percentage;
    private JTextField period;
    private JTextField monthPayment;
    private JTextField overpayments1;
    private JTextField overpayments2;
    JButton calcButton;

    static double nCredit = 0;
    static double nPerc = 0;
    static double nPeriod = 0;
    static double nMonPay = 0;
    static double res1 = 0;
    static double res2 = 0;

    Calc() {
        f = new JFrame("Calculator");

        l1 = new JLabel("Loan");
        l2 = new JLabel("Percentage");
        l3 = new JLabel("Period (months)");
        l4 = new JLabel("Monthly payment");
        l5 = new JLabel("Overpayments #v1");
        l6 = new JLabel("Overpayments #v2");

        l1.setBounds(30, 40, 120, 30);
        l2.setBounds(30, 100, 120, 30);
        l3.setBounds(30, 160, 120, 30);
        l4.setBounds(30, 220, 120, 30);
        l5.setBounds(30, 350, 120, 30);

        loan = new JTextField();
        percentage = new JTextField();
        period = new JTextField();
        monthPayment = new JTextField();
        overpayments1 = new JTextField();
        overpayments2 = new JTextField();


        loan.setBounds(160, 40, 120, 30);
        percentage.setBounds(160, 120, 120, 30);
        period.setBounds(160, 160, 120, 30);
        monthPayment.setBounds(160, 220, 120, 30);
        overpayments1.setBounds(160, 350, 120, 30);
        overpayments2.setBounds(160, 420, 120, 30);

        calcButton = new JButton("Calculate");
        calcButton.setBounds(95, 290, 120, 40);

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);

        f.add(loan);
        f.add(percentage);
        f.add(period);
        f.add(monthPayment);
        f.add(overpayments1);
        f.add(overpayments2);

        f.add(calcButton);

        f.setLayout(null);
        f.setSize(310, 540);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calcButton.addActionListener(this);
    }

/*1. Ежемесячный аннуитетный платеж:

A = K*S, где:

A — ежемесячный аннуитетный платеж,
K — коэффициент аннуитета,
S — сумма кредита.

2. Коэффициент аннуитета:

K=i*(1+i)n/(1+i)n-1, где:

K — коэффициент аннуитета,
i — месячная процентная ставка по кредиту (= годовая ставка/12 месяцев),
n — количество периодов, в течение которых выплачивается кредит.*/


/*Срок кредита	10 лет
Сумма кредита	3 200 000,00
Ставка	15%
Ежемесячный платеж	51 627,19
Сумма переплаты	2 995 261,75*/

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != calcButton)
            return;

        nCredit = Double.parseDouble(loan.getText());
        nPerc = Double.parseDouble(percentage.getText()) / 1200;
        nPeriod = Double.parseDouble(period.getText());

        /*Average calculation*/
        res1 = ((nPerc * Math.pow(1 + nPerc, nPeriod)) / (Math.pow(1 + nPerc, nPeriod) - 1))
                * (nCredit);

        /*Actual calculation*/
        res2 = ((nPerc ) / (1 - Math.pow(1 + nPerc, - (nPeriod - 1))))
                * (nCredit);

        monthPayment.setText("" + new DecimalFormat("#.##").format(res2));
        overpayments1.setText("" + new DecimalFormat("#.##").format((res1 * nPeriod) - (nCredit)));
        overpayments2.setText("" + new DecimalFormat("#.##").format((res2 * nPeriod) - (nCredit)));
    }

    public static void main(String... s) {
        new Calc();
    }

}
