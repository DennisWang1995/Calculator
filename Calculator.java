package com.wy.calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.math.BigDecimal;

public class Calculator {

	// ������1��Ϊ�˳���İ�ȫ����ֵһ�����ã�������������Ϊ0��
	String str_1 = "0";

	// ������2
	String str_2 = "0";

	// �����
	String signal = "+";

	// ������
	String result = "";

	// ����k1��k2Ϊ״̬����

	// ����1����ѡ�����뷽�򣬽�Ҫд��str1��str2
	int key_1 = 1;
	// ����2���ڼ�¼���ż��Ĵ�������� k2>1 ˵�����е��� 2+3-9+8 �����Ķ��������
	int key_2 = 1;
	// ����3���ڱ�ʶ str1 �Ƿ���Ա���0 ������1ʱ���ԣ�������1ʱ���ܱ���0
	int key_3 = 1;
	// ����4���ڱ�ʶ str2 �Ƿ���Ա���0
	int key_4 = 1;
	// ����5���ڿ���С����ɷ�¼�룬����1ʱ���ԣ���Ϊ1ʱ�������С���㱻����
	int key_5 = 1;
	// store�����������ڼĴ��������ڼ�¼�Ƿ��������·��ż�
	JButton store;

	@SuppressWarnings("rawtypes")
	Vector vt = new Vector(20, 10);

	// ��������UI������󲢳�ʼ��
	JFrame frame = new JFrame("Calculator");
	JTextField result_TextField = new JTextField(result, 20);
	JButton clear_Button = new JButton("Clear");
	JButton button0 = new JButton("0");
	JButton button1 = new JButton("1");
	JButton button2 = new JButton("2");
	JButton button3 = new JButton("3");
	JButton button4 = new JButton("4");
	JButton button5 = new JButton("5");
	JButton button6 = new JButton("6");
	JButton button7 = new JButton("7");
	JButton button8 = new JButton("8");
	JButton button9 = new JButton("9");
	JButton button_point = new JButton(".");
	JButton button_add = new JButton("+");
	JButton button_minus = new JButton("-");
	JButton button_multiply = new JButton("*");
	JButton button_divide = new JButton("/");
	JButton button_equal = new JButton("=");

	// �������Ĺ�����
	public Calculator() {

		// Ϊ��ť���õ�Ч����������ͨ����Ӧ�ļ��̰�������������
		button0.setMnemonic(KeyEvent.VK_0);
		// ������Ч��ʡ�ԣ���������в�������

		// �����ı���Ϊ�Ҷ��룬ʹ����ͽ����������ʾ
		result_TextField.setHorizontalAlignment(JTextField.RIGHT);

		// ��UI�����ӽ�������
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(4, 4, 5, 5));
		pan.add(button7);
		pan.add(button8);
		pan.add(button9);
		pan.add(button_divide);
		pan.add(button4);
		pan.add(button5);
		pan.add(button6);
		pan.add(button_multiply);
		pan.add(button1);
		pan.add(button2);
		pan.add(button3);
		pan.add(button_minus);
		pan.add(button0);
		pan.add(button_point);
		pan.add(button_equal);
		pan.add(button_add);
		pan.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel pan2 = new JPanel();
		pan2.setLayout(new BorderLayout());
		pan2.add(result_TextField, BorderLayout.WEST);
		pan2.add(clear_Button, BorderLayout.EAST);

		// ���������ڳ�������Ļ�ϵ�λ��
		frame.setLocation(300, 200);
		// ���ô��岻�ܵ���С
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(pan2, BorderLayout.NORTH);
		frame.getContentPane().add(pan, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);

		// �¼��������

		// ���ּ�
		class Listener implements ActionListener {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String ss = ((JButton) e.getSource()).getText();
				store = (JButton) e.getSource();
				vt.add(store);
				if (key_1 == 1) {
					if (key_3 == 1) {
						str_1 = "";

						// ��ԭ����k5״̬
						key_5 = 1;
					}
					str_1 = str_1 + ss;

					key_3 = key_3 + 1;

					// ��ʾ���
					result_TextField.setText(str_1);

				} else if (key_1 == 2) {
					if (key_4 == 1) {
						str_2 = "";

						// ��ԭ����k5״̬
						key_5 = 1;
					}
					str_2 = str_2 + ss;
					key_4 = key_4 + 1;
					result_TextField.setText(str_2);
				}

			}
		}

		// �����������ŵĴ���
		class Listener_signal implements ActionListener {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String ss2 = ((JButton) e.getSource()).getText();
				store = (JButton) e.getSource();
				vt.add(store);

				if (key_2 == 1) {
					// ���� k1 Ϊ 1 ʱ���� 1 д����ֵ��Ϊ2ʱ����2д����ֵ��
					key_1 = 2;
					key_5 = 1;
					signal = ss2;
					key_2 = key_2 + 1;// �����ż��Ĵ���
				} else {
					int a = vt.size();
					JButton c = (JButton) vt.get(a - 2);

					if (!(c.getText().equals("+")) && !(c.getText().equals("-")) && !(c.getText().equals("*"))
							&& !(c.getText().equals("/")))

					{
						cal();
						str_1 = result;
						// ���� k1 Ϊ 1 ʱ������ 1 дֵ��Ϊ2ʱ����2д
						key_1 = 2;
						key_5 = 1;
						key_4 = 1;
						signal = ss2;
					}
					key_2 = key_2 + 1;

				}

			}
		}

		// ��������߼���Clear��
		class Listener_clear implements ActionListener {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				store = (JButton) e.getSource();
				vt.add(store);
				key_5 = 1;
				key_2 = 1;
				key_1 = 1;
				key_3 = 1;
				key_4 = 1;
				str_1 = "0";
				str_2 = "0";
				signal = "";
				result = "";
				result_TextField.setText(result);
				vt.clear();
			}
		}

		// ���ڼ����߼�
		class Listener_dy implements ActionListener {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				store = (JButton) e.getSource();
				vt.add(store);
				cal();

				// ��ԭ�������ص�״̬
				key_1 = 1;
				key_2 = 1;
				key_3 = 1;
				key_4 = 1;

				str_1 = result;
			}
		}

		// С����Ĵ���
		class Listener_xiaos implements ActionListener {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				store = (JButton) e.getSource();
				vt.add(store);
				if (key_5 == 1) {
					String ss2 = ((JButton) e.getSource()).getText();
					if (key_1 == 1) {
						if (key_3 == 1) {
							str_1 = "";
							// ��ԭ����k5״̬
							key_5 = 1;
						}
						str_1 = str_1 + ss2;

						key_3 = key_3 + 1;

						// ��ʾ���
						result_TextField.setText(str_1);

					} else if (key_1 == 2) {
						if (key_4 == 1) {
							str_2 = "";
							// ��ԭ����k5��״̬
							key_5 = 1;
						}
						str_2 = str_2 + ss2;

						key_4 = key_4 + 1;

						result_TextField.setText(str_2);
					}
				}

				key_5 = key_5 + 1;
			}
		}

		// ע������������������¼���Ӧ�߼�������UI�����
		Listener_dy jt_dy = new Listener_dy();

		// �������ּ�
		Listener jt = new Listener();
		// �������ż�
		Listener_signal jt_signal = new Listener_signal();
		// ���������
		Listener_clear jt_c = new Listener_clear();
		// ����С�����
		Listener_xiaos jt_xs = new Listener_xiaos();

		button7.addActionListener(jt);
		button8.addActionListener(jt);
		button9.addActionListener(jt);
		button_divide.addActionListener(jt_signal);
		button4.addActionListener(jt);
		button5.addActionListener(jt);
		button6.addActionListener(jt);
		button_multiply.addActionListener(jt_signal);
		button1.addActionListener(jt);
		button2.addActionListener(jt);
		button3.addActionListener(jt);
		button_minus.addActionListener(jt_signal);
		button0.addActionListener(jt);
		button_point.addActionListener(jt_xs);
		button_equal.addActionListener(jt_dy);
		button_add.addActionListener(jt_signal);
		clear_Button.addActionListener(jt_c);

		// ����ر��¼�����Ӧ����
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	// �����߼�
	public void cal() {
		// ������1
		double a2;
		// ������2
		double b2;
		// �����
		String c = signal;
		// ������
		double result2 = 0;

		if (c.equals("")) {
			result_TextField.setText("Please input operator");

		} else {
			// �ֶ�����С���������
			if (str_1.equals("."))
				str_1 = "0.0";
			if (str_2.equals("."))
				str_2 = "0.0";
			a2 = Double.valueOf(str_1).doubleValue();
			b2 = Double.valueOf(str_2).doubleValue();

			if (c.equals("+")) {
				result2 = a2 + b2;
			}
			if (c.equals("-")) {
				result2 = a2 - b2;
			}
			if (c.equals("*")) {
				BigDecimal m1 = new BigDecimal(Double.toString(a2));
				BigDecimal m2 = new BigDecimal(Double.toString(b2));
				result2 = m1.multiply(m2).doubleValue();
			}
			if (c.equals("/")) {
				if (b2 == 0) {
					result2 = 0;
				} else {
					result2 = a2 / b2;
				}

			}

			result = ((new Double(result2)).toString());

			result_TextField.setText(result);
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// ���ó�����ʾ�Ľ����񣬿���ȥ��
		/*
		 * Windows ���com.sun.java.swing.plaf.windows.WindowsLookAndFeel 
		 * Metal���Ĭ�ϣ���javax.swing.plaf.metal.MetalLookAndFeel 
		 * ����Ϊ Motif���com.sun.java.swing.plaf.motif.MotifLookAndFeel 
		 * ����Ϊ Mac���com.sun.java.swing.plaf.mac.MacLookAndFeel 
		 * ����Ϊ GTK���com.sun.java.swing.plaf.gtk.GTKLookAndFeel
		 */
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calculator cal = new Calculator();
	}

}