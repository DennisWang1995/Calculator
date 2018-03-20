package com.wy.calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.math.BigDecimal;

public class Calculator {

	// 操作数1，为了程序的安全，初值一定设置，这里我们设置为0。
	String str_1 = "0";

	// 操作数2
	String str_2 = "0";

	// 运算符
	String signal = "+";

	// 运算结果
	String result = "";

	// 以下k1至k2为状态开关

	// 开关1用于选择输入方向，将要写入str1或str2
	int key_1 = 1;
	// 开关2用于记录符号键的次数，如果 k2>1 说明进行的是 2+3-9+8 这样的多符号运算
	int key_2 = 1;
	// 开关3用于标识 str1 是否可以被清0 ，等于1时可以，不等于1时不能被清0
	int key_3 = 1;
	// 开关4用于标识 str2 是否可以被清0
	int key_4 = 1;
	// 开关5用于控制小数点可否被录入，等于1时可以，不为1时，输入的小数点被丢掉
	int key_5 = 1;
	// store的作用类似于寄存器，用于记录是否连续按下符号键
	JButton store;

	@SuppressWarnings("rawtypes")
	Vector vt = new Vector(20, 10);

	// 声明各个UI组件对象并初始化
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

	// 计算机类的构造器
	public Calculator() {

		// 为按钮设置等效键，即可以通过对应的键盘按键来代替点击它
		button0.setMnemonic(KeyEvent.VK_0);
		// 其它等效键省略，你可以自行补充完整

		// 设置文本框为右对齐，使输入和结果都靠右显示
		result_TextField.setHorizontalAlignment(JTextField.RIGHT);

		// 将UI组件添加进容器内
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

		// 设置主窗口出现在屏幕上的位置
		frame.setLocation(300, 200);
		// 设置窗体不能调大小
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(pan2, BorderLayout.NORTH);
		frame.getContentPane().add(pan, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);

		// 事件处理程序

		// 数字键
		class Listener implements ActionListener {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String ss = ((JButton) e.getSource()).getText();
				store = (JButton) e.getSource();
				vt.add(store);
				if (key_1 == 1) {
					if (key_3 == 1) {
						str_1 = "";

						// 还原开关k5状态
						key_5 = 1;
					}
					str_1 = str_1 + ss;

					key_3 = key_3 + 1;

					// 显示结果
					result_TextField.setText(str_1);

				} else if (key_1 == 2) {
					if (key_4 == 1) {
						str_2 = "";

						// 还原开关k5状态
						key_5 = 1;
					}
					str_2 = str_2 + ss;
					key_4 = key_4 + 1;
					result_TextField.setText(str_2);
				}

			}
		}

		// 输入的运算符号的处理
		class Listener_signal implements ActionListener {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String ss2 = ((JButton) e.getSource()).getText();
				store = (JButton) e.getSource();
				vt.add(store);

				if (key_2 == 1) {
					// 开关 k1 为 1 时向数 1 写输入值，为2时向数2写输入值。
					key_1 = 2;
					key_5 = 1;
					signal = ss2;
					key_2 = key_2 + 1;// 按符号键的次数
				} else {
					int a = vt.size();
					JButton c = (JButton) vt.get(a - 2);

					if (!(c.getText().equals("+")) && !(c.getText().equals("-")) && !(c.getText().equals("*"))
							&& !(c.getText().equals("/")))

					{
						cal();
						str_1 = result;
						// 开关 k1 为 1 时，向数 1 写值，为2时向数2写
						key_1 = 2;
						key_5 = 1;
						key_4 = 1;
						signal = ss2;
					}
					key_2 = key_2 + 1;

				}

			}
		}

		// 清除键的逻辑（Clear）
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

		// 等于键的逻辑
		class Listener_dy implements ActionListener {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				store = (JButton) e.getSource();
				vt.add(store);
				cal();

				// 还原各个开关的状态
				key_1 = 1;
				key_2 = 1;
				key_3 = 1;
				key_4 = 1;

				str_1 = result;
			}
		}

		// 小数点的处理
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
							// 还原开关k5状态
							key_5 = 1;
						}
						str_1 = str_1 + ss2;

						key_3 = key_3 + 1;

						// 显示结果
						result_TextField.setText(str_1);

					} else if (key_1 == 2) {
						if (key_4 == 1) {
							str_2 = "";
							// 还原开关k5的状态
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

		// 注册各个监听器，即绑定事件响应逻辑到各个UI组件上
		Listener_dy jt_dy = new Listener_dy();

		// 监听数字键
		Listener jt = new Listener();
		// 监听符号键
		Listener_signal jt_signal = new Listener_signal();
		// 监听清除键
		Listener_clear jt_c = new Listener_clear();
		// 监听小数点键
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

		// 窗体关闭事件的响应程序
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	// 计算逻辑
	public void cal() {
		// 操作数1
		double a2;
		// 操作数2
		double b2;
		// 运算符
		String c = signal;
		// 运算结果
		double result2 = 0;

		if (c.equals("")) {
			result_TextField.setText("Please input operator");

		} else {
			// 手动处理小数点的问题
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
		// 设置程序显示的界面风格，可以去除
		/*
		 * Windows 风格：com.sun.java.swing.plaf.windows.WindowsLookAndFeel 
		 * Metal风格（默认）：javax.swing.plaf.metal.MetalLookAndFeel 
		 * 更换为 Motif风格：com.sun.java.swing.plaf.motif.MotifLookAndFeel 
		 * 更换为 Mac风格：com.sun.java.swing.plaf.mac.MacLookAndFeel 
		 * 更换为 GTK风格：com.sun.java.swing.plaf.gtk.GTKLookAndFeel
		 */
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.mac.MacLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Calculator cal = new Calculator();
	}

}