package FlipCARDS;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;   //网格布局

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.OptionPaneUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
	JButton[] btnAry;
	JLabel lab_jindu;
	JLabel lab_bushu;
	JButton btn_restart ;
	JButton btn_exit;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setLocationRelativeTo(frame);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btn_exit.doClick();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 495, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		btn_restart = new JButton("\u91CD\u65B0\u5F00\u59CB");
		//采用匿名类的方法，为重新开始按钮添加监听器
		btn_restart.addActionListener(new ActionListener() {
			//事件处理方法
			public void actionPerformed(ActionEvent e) {
				//按钮变粉
				//数据清零
				for(int i=0;i<9*9;i++) {
					btnAry[i].setBackground(Color.PINK);
				}
				count=0;
				tab=0;
				lab_jindu.setText("当前进度：");
				lab_bushu.setText("步数：");
				
				
			}
		});
		btn_restart.setBounds(68, 512, 114, 33);
		contentPane.add(btn_restart);
		//退出
		btn_exit = new JButton("\u9000\u51FA\u6E38\u620F");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res=JOptionPane.showConfirmDialog(null, "是否退出？", "确定继续", JOptionPane.YES_NO_OPTION);
				if(res==JOptionPane.YES_OPTION) {System.exit(0);}
			}
		});
		btn_exit.setBounds(250, 512, 114, 33);
		contentPane.add(btn_exit);
		
		lab_jindu = new JLabel("\u5F53\u524D\u8FDB\u5EA6\uFF1A");
		lab_jindu.setBounds(34, 0, 85, 18);
		contentPane.add(lab_jindu);
		
		lab_bushu = new JLabel("\u6B65\u6570\uFF1A");
		lab_bushu.setBounds(260, 0, 72, 18);
		contentPane.add(lab_bushu);
		
		//游戏面板（网格布局）
		JPanel pan_game = new JPanel();
		pan_game.setBounds(0, 40, 477, 466);
		contentPane.add(pan_game);
		
		//网格布局
		GridLayout gl=new GridLayout(9,9);
		pan_game.setLayout(gl);
		
		JButton btn_god = new JButton("\u4E0A\u5E1D\u6A21\u5F0F");
		btn_god.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//离胜利差一步
				for (int i = 0; i < btnAry.length; i++) {
					btnAry[i].setBackground(Color.BLUE);
				}
				
				btnAry[0].setBackground(Color.PINK);
				btnAry[1].setBackground(Color.PINK);
				btnAry[9].setBackground(Color.PINK);
				
			}
		});
		btn_god.setBounds(384, 518, 93, 27);
		contentPane.add(btn_god);
		
		//按钮
		btnAry=new JButton[9*9];
		
		MyListener ml=new MyListener();
		
		for(int i=0;i<btnAry.length;i++) {
			btnAry[i]=new JButton();
			pan_game.add(btnAry[i]);    //加入按钮
			btnAry[i].setBackground(Color.PINK);
			btnAry[i].addActionListener(ml);
			
			//索引文本
			//btnAry[i].setText(i+"");
			
			//给按钮加标识
			btnAry[i].setActionCommand(i+"");
		}
		
			
	}
	int tab=0;
	int count;
	
	//一个监听器监听所有按钮，完成游戏规则
	//内部类方式实现
    class MyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
        String str=e.getActionCommand();
        int index=Integer.parseInt(str);
		
         changColor(index);
         
         if(index%9!=0) {
        	 changColor(index-1); 
         }
         if((index+1)%9!=0) {
         changColor(index+1);
         }
         if(index<9*(9-1)) {
         changColor(index+9);	 
         }
         if(index>=9) {
         changColor(index-9); 
         }
         
         
         //刷新进度和步数
         //是否已经胜利
         count=getBlueCounte();
         lab_jindu.setText("当前进度："+count);
         lab_bushu.setText("步数："+(++tab));
         if(count==9*9) {
        	 JOptionPane.showConfirmDialog(null, "你赢了！", "是否继续", JOptionPane.YES_NO_OPTION);
        	 btn_restart.doClick(); 
         }
         
         
         
        
		}
		
	}
    
    private void changColor(int index) {
        if(btnAry[index].getBackground().equals(Color.pink)) {
        	btnAry[index].setBackground(Color.blue);
        }else {
        	btnAry[index].setBackground(Color.pink);
        }
    }
    
    private int getBlueCounte() {
    	int count=0;
    	for(int i=0;i<btnAry.length;i++) {
    		if(btnAry[i].getBackground().equals(Color.BLUE)) {
    			count++;
    		}
    	}
    	
		return count;
    	
    }
}
