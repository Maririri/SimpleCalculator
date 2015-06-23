/**
 * Created by Mari on 08/05/15.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class Calc implements ActionListener {

    JFrame f;

    private JLabel l1, l2, l3, l4, l5, l6;
    private JTextField initPayment;
    private JTextField totalSum;
    private JTextField percentage;
    private JTextField period;
    private JTextField monthPayment;
    private JTextField overpayments;
    JButton calcButton;

    static double nInitPay = 0, nTotSum = 0, nPerc = 0, nPeriod = 0, nMonPay = 0, res = 0;

    Calc() {
        f = new JFrame("Calculator");

        l1 = new JLabel("Initial payment");
        l2 = new JLabel("Total Sum");
        l3 = new JLabel("Percentage");
        l4 = new JLabel("Period");
        l5 = new JLabel("Monthly payment");
        l6 = new JLabel("Overpayments");

        l1.setBounds(30, 40, 120, 30);
        l2.setBounds(30, 100, 120, 30);
        l3.setBounds(30, 160, 120, 30);
        l4.setBounds(30, 220, 120, 30);
        l5.setBounds(30, 350, 120, 30);
        l6.setBounds(30, 410, 120, 30);

        initPayment = new JTextField();
        totalSum = new JTextField();
        percentage = new JTextField();
        period = new JTextField();
        monthPayment = new JTextField();
        overpayments = new JTextField();


        initPayment.setBounds(160, 40, 120, 30);
        totalSum.setBounds(160, 100, 120, 30);
        percentage.setBounds(160, 160, 120, 30);
        period.setBounds(160, 220, 120, 30);
        monthPayment.setBounds(160, 350, 120, 30);
        overpayments.setBounds(160, 410, 120, 30);

        calcButton = new JButton("Calculate");
        calcButton.setBounds(95, 290, 120, 40);

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);

        f.add(initPayment);
        f.add(totalSum);
        f.add(percentage);
        f.add(period);
        f.add(monthPayment);
        f.add(overpayments);

        f.add(calcButton);

        f.setLayout(null);
        f.setSize(310, 480);
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

        nInitPay = Double.parseDouble(initPayment.getText());
        nTotSum = Double.parseDouble(totalSum.getText());
        nPerc = Double.parseDouble(percentage.getText()) / 1200;
        nPeriod = Double.parseDouble(period.getText()) * 12;

        res = ((nPerc * Math.pow(1 + nPerc, nPeriod)) / (Math.pow(1 + nPerc, nPeriod) - 1))
                * (nTotSum - nInitPay);

        monthPayment.setText("" + new DecimalFormat("#.##").format(res));
        overpayments.setText("" + new DecimalFormat("#.##").format((res * nPeriod) - (nTotSum - nInitPay)));
    }

    public static void main(String... s) {
        new Calc();
    }

}
