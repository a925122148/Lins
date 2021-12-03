package com.sxt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO; 

public class StaticValue {
	//常量类，控制启动初始背景
	
	//背景，其中bg1为第一二关背景，bg2为第三关背景
	public static BufferedImage bg = null;
	public static BufferedImage bg2 = null;
	//向左跳跃
	public static BufferedImage jump_L = null;
	//向右跳跃
	public static BufferedImage jump_R = null;
	//向左站立
	public static BufferedImage stand_L = null;
	//向右站立
	public static BufferedImage stand_R = null;
	//城堡
	public static BufferedImage burger = null;
	//旗杆
	public static BufferedImage gan = null;
	//障碍物
	public static List<BufferedImage> obstacle = new ArrayList<>();
	//向左跑
	public static List<BufferedImage> run_L = new ArrayList<>();
	//向右跑
	public static List<BufferedImage> run_R = new ArrayList<>();
	//蘑菇怪
	public static List<BufferedImage> mogu = new ArrayList<>();
	//食人花
	public static List<BufferedImage> flower = new ArrayList<>();
	//路径前缀，方便后续调用图片
	public static String path = System.getProperty("user.dir") + "/src/images/";
	
	//初始化方法
	public static void init() {

		try {		
			//加载背景图片
			bg = ImageIO.read(new File(path + "bg.png"));
			bg2 = ImageIO.read(new File(path + "bg2.png"));
			//加载向左站立
			stand_L = ImageIO.read(new File(path + "stand_L.png"));
			//加载向右站立
			stand_R = ImageIO.read(new File(path + "stand_R.png"));
			//加载城堡
			burger = ImageIO.read(new File(path + "burger.png"));
			//加载旗杆
			gan = ImageIO.read(new File(path + "gan.png"));
			//加载向左跳跃
			jump_L = ImageIO.read(new File(path + "jump_L.png"));
			//加载向右跳跃
			jump_R = ImageIO.read(new File(path + "jump_R.png"));
			
			} catch (IOException e) {
			//捕获异常
			e.printStackTrace();
		}
		
			//加载向左跑
		for(int i = 1;i <= 2;i++) {
			try {
				run_L.add(ImageIO.read(new File(path + "s_kunrio_run" +i+"_L.png")));
				
				}catch (IOException e) {
				e.printStackTrace();
		}
	}
			//加载向右跑
		for(int i = 1;i <= 2;i++) {
			try {
				run_R.add(ImageIO.read(new File(path + "s_kunrio_run" +i+"_R.png")));
				
				}catch (IOException e){
				e.printStackTrace();
		}
	}
			//加载障碍物
			try {
				obstacle.add(ImageIO.read(new File(path + "brick1.png")));
				obstacle.add(ImageIO.read(new File(path + "soil_up.png")));
				obstacle.add(ImageIO.read(new File(path + "soil_base.png")));
				}catch (IOException e){
				e.printStackTrace();
		}
			
			//加载水管
		for(int i = 1;i <= 4;i++) {
			try {
				obstacle.add(ImageIO.read(new File(path +  "pipe" +i+".png")));
				}catch (IOException e){
				e.printStackTrace();
		}
	}
			//加载砖块与旗子（不可破坏）
			try {
				obstacle.add(ImageIO.read(new File(path + "brick2.png")));
				obstacle.add(ImageIO.read(new File(path + "flag.png")));
				}catch (IOException e){
					e.printStackTrace();
		}
			
			//加载蘑菇怪
		for(int i = 1;i <= 3;i++) {
			try {
				mogu.add(ImageIO.read(new File(path + "fungus" +i+".png")));
				
				}catch (IOException e){
				e.printStackTrace();
		}
	}
		
			//加载食人花
		for(int i = 1;i <= 2;i++) {
			try {
				flower.add(ImageIO.read(new File(path + "flower" +i+".png")));
				
				}catch (IOException e){
				e.printStackTrace();
		}
	}
}
}