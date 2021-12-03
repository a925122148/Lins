package com.sxt;

import java.awt.*;
import javax.swing.*;

import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener,Runnable{

	private static final long serialVersionUID = 3074481834439888113L;

	//用于存储所有的背景
	private List<BackGround> allBg = new ArrayList<>();
	
	//用于存储当前的背景
	private BackGround nowBg = new BackGround();
	
	//用于双缓存
	private Image offScreenImage = null;
	
	//定义玩家所控制角色
	private Kunrio kunrio = new Kunrio();
	
	//定义实现玩家移动的线程
	private Thread thread = new Thread(this);
	
	public MyFrame() {
		//设置初始窗口大小为800*600
		this.setSize(800, 600);
		
		//设置窗口居中显示
		this.setLocationRelativeTo(null);
		
		//设置窗口可视化
		this.setVisible(true);
		
		//设置关闭按钮
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置窗口大小可变
		this.setResizable(false);
		
		//向窗口添加键盘监听器
		this.addKeyListener(this);
		
		//设置窗口名称
		this.setTitle("超级坤里奥");
		
		//初始化图片
		StaticValue.init();
		
		//初始化玩家控制对象，出生坐标为x=10，y=355
		kunrio = new Kunrio(10,355);
		
		//创建全部场景
		for(int i=1;i<=3;i++)
		{
			allBg.add(new BackGround(i,i==3?true:false));
		}
		
		//将第一个场景设置为当前场景
		nowBg=allBg.get(0);
		kunrio.setBackGround(nowBg);
		
		//绘制图像
		repaint();
		
		//启动线程
		thread.start();
		
		//播放音乐
		try {
			new Music();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void paint(Graphics g) {
		if(offScreenImage==null) {
			offScreenImage = createImage(800,600);
		}
		
		Graphics graphics = offScreenImage.getGraphics();
		graphics.fillRect(0,0,800,600);
		
		//绘制背景
		graphics.drawImage(nowBg.getBgImage(),0,0,this);
		
		//绘制敌人
		for(Enemy e :nowBg.getEnemyList()) {
			graphics.drawImage(e.getShow(), e.getX(), e.getY(),this);
		}
		
		//绘制障碍物
		for(Obstacle ob:nowBg.getObstacleList()) {
			graphics.drawImage(ob.getShow(),ob.getX(),ob.getY(),this);
		}
		
		//绘制城堡
		graphics.drawImage(nowBg.getBurger(),620,270,this);
		
		//绘制旗杆
		graphics.drawImage(nowBg.getGan(),500,220,this);
		
		//绘制玩家所控制角色
		graphics.drawImage(kunrio.getShow(),kunrio.getX(),kunrio.getY(),this);
		
		//输出分数
		Color c = graphics.getColor();
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("黑体",Font.CENTER_BASELINE,20));//设置字体
		graphics.drawString("当前得分：" + kunrio.getScore(),300,100);
		graphics.setColor(c);
		
		//将图像绘制到窗口中
		g.drawImage(offScreenImage, 0, 0, this);
	}
	
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	//按下按键时调用
	@Override
	public void keyPressed(KeyEvent e) {
		//向右移动,39为右箭头
		if(e.getKeyCode()==39) {
			kunrio.rightMove();
		}
		//向左移动，37为左箭头
		if(e.getKeyCode()==37) {
			kunrio.leftMove();
		}
		//跳跃，38为上箭头
		if(e.getKeyCode()==38) {
			kunrio.jump();
		}
}
	
	//松开按键时调用
	@Override
	public void keyReleased(KeyEvent e) {
		//向右停止
		if(e.getKeyCode()==39) {
			kunrio.rightStop();
		}
		//向左停止
		if(e.getKeyCode()==37) {
			kunrio.leftStop();
		}
	}

	@Override
	public void run() {
		while(true) {
			repaint();
			try {
				Thread.sleep(50);
				//场景大小为800，坤坤大小为25，此处用于判断是否到了屏幕最右端
				if(kunrio.getX()>=775) {
					nowBg = allBg.get(nowBg.getSort());
					kunrio.setBackGround(nowBg);
					kunrio.setX(10);
					kunrio.setY(355);
				}
				
				//判断坤坤是否失败
				if(kunrio.isDeath()) {
					JOptionPane.showMessageDialog(this,"坤坤被世界磨平了棱角QAQ" );
					System.exit(0);
				}
				
				//判断游戏是否结束{
				if(kunrio.isOK()) {
					JOptionPane.showMessageDialog(this,"坤坤称霸了世界！" );
					System.exit(0);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
