package com.sxt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO; 

public class StaticValue {
	//�����࣬����������ʼ����
	
	//����������bg1Ϊ��һ���ر�����bg2Ϊ�����ر���
	public static BufferedImage bg = null;
	public static BufferedImage bg2 = null;
	//������Ծ
	public static BufferedImage jump_L = null;
	//������Ծ
	public static BufferedImage jump_R = null;
	//����վ��
	public static BufferedImage stand_L = null;
	//����վ��
	public static BufferedImage stand_R = null;
	//�Ǳ�
	public static BufferedImage burger = null;
	//���
	public static BufferedImage gan = null;
	//�ϰ���
	public static List<BufferedImage> obstacle = new ArrayList<>();
	//������
	public static List<BufferedImage> run_L = new ArrayList<>();
	//������
	public static List<BufferedImage> run_R = new ArrayList<>();
	//Ģ����
	public static List<BufferedImage> mogu = new ArrayList<>();
	//ʳ�˻�
	public static List<BufferedImage> flower = new ArrayList<>();
	//·��ǰ׺�������������ͼƬ
	public static String path = System.getProperty("user.dir") + "/src/images/";
	
	//��ʼ������
	public static void init() {

		try {		
			//���ر���ͼƬ
			bg = ImageIO.read(new File(path + "bg.png"));
			bg2 = ImageIO.read(new File(path + "bg2.png"));
			//��������վ��
			stand_L = ImageIO.read(new File(path + "stand_L.png"));
			//��������վ��
			stand_R = ImageIO.read(new File(path + "stand_R.png"));
			//���سǱ�
			burger = ImageIO.read(new File(path + "burger.png"));
			//�������
			gan = ImageIO.read(new File(path + "gan.png"));
			//����������Ծ
			jump_L = ImageIO.read(new File(path + "jump_L.png"));
			//����������Ծ
			jump_R = ImageIO.read(new File(path + "jump_R.png"));
			
			} catch (IOException e) {
			//�����쳣
			e.printStackTrace();
		}
		
			//����������
		for(int i = 1;i <= 2;i++) {
			try {
				run_L.add(ImageIO.read(new File(path + "s_kunrio_run" +i+"_L.png")));
				
				}catch (IOException e) {
				e.printStackTrace();
		}
	}
			//����������
		for(int i = 1;i <= 2;i++) {
			try {
				run_R.add(ImageIO.read(new File(path + "s_kunrio_run" +i+"_R.png")));
				
				}catch (IOException e){
				e.printStackTrace();
		}
	}
			//�����ϰ���
			try {
				obstacle.add(ImageIO.read(new File(path + "brick1.png")));
				obstacle.add(ImageIO.read(new File(path + "soil_up.png")));
				obstacle.add(ImageIO.read(new File(path + "soil_base.png")));
				}catch (IOException e){
				e.printStackTrace();
		}
			
			//����ˮ��
		for(int i = 1;i <= 4;i++) {
			try {
				obstacle.add(ImageIO.read(new File(path +  "pipe" +i+".png")));
				}catch (IOException e){
				e.printStackTrace();
		}
	}
			//����ש�������ӣ������ƻ���
			try {
				obstacle.add(ImageIO.read(new File(path + "brick2.png")));
				obstacle.add(ImageIO.read(new File(path + "flag.png")));
				}catch (IOException e){
					e.printStackTrace();
		}
			
			//����Ģ����
		for(int i = 1;i <= 3;i++) {
			try {
				mogu.add(ImageIO.read(new File(path + "fungus" +i+".png")));
				
				}catch (IOException e){
				e.printStackTrace();
		}
	}
		
			//����ʳ�˻�
		for(int i = 1;i <= 2;i++) {
			try {
				flower.add(ImageIO.read(new File(path + "flower" +i+".png")));
				
				}catch (IOException e){
				e.printStackTrace();
		}
	}
}
}