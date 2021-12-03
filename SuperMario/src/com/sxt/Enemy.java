package com.sxt;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable {
	//�洢��ǰ����
	private int x;
	private int y;
	//�洢��������,type=1ʱ����ΪĢ����,type=2ʱ����Ϊʳ�˻���
	private int type;
	//�жϵ����˶��ķ���,trueʱ��ʾĢ���ֳ����˶�,ʳ�˻������ƶ�
	private boolean face_to =true;
	//������ʾ���˵ĵ�ǰͼ��
	private BufferedImage show;
	//���屳������
	private BackGround bg;
	//ʳ�˻��˶��ļ��޷�Χ
	private int max_up = 0;
	private int max_down = 0;
	//�����̣߳�ʵ�ֵ����˶�
	private Thread thread = new Thread(this);
	//���嵱ǰͼƬ״̬
	private int image_type = 0;
	
	//Ģ���ֹ��캯��
	public Enemy(int x,int y,boolean face_to,int type,BackGround bg) {
		this.x = x;
		this.y = y;
		this.face_to = face_to;
		this.type = type;
		this.bg = bg;
		show = StaticValue.mogu.get(0);
		thread.start();
	}

	//ʳ�˻����캯��
	public Enemy(int x,int y,boolean face_to,int type,int max_up,int max_down,BackGround bg) {
		this.x = x;
		this.y = y;
		this.face_to = face_to;
		this.type = type;
		this.bg = bg;
		this.max_up = max_up;
		this.max_down = max_down;
		show = StaticValue.flower.get(0);
		thread.start();
	}
	
	//��������,���ֻ��ɱ��Ģ����,����ɱ��ʳ�˻�
	public void death() {
		show = StaticValue.mogu.get(2);
		this.bg.getEnemyList().remove(this);
	}
	

	public BufferedImage getShow() {
		return show;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getType() {
		return type;
	}
	
	@Override
	public void run() {
		while(true) {
			//�����Ƿ�ΪĢ����
			if(type == 1) {//��ʱ����ΪĢ����
				if(face_to) {//��ʱface_toΪtrue�����������ƶ�
					this.x -= 2;
				}else {//��ʱface_toΪfalse�����������ƶ�
					this.x += 2;
				}			
				image_type = image_type == 1 ? 0 : 1 ;
			
				show = StaticValue.mogu.get(image_type);
			}

			//�����������������������ж�Ģ�����Ƿ��������������ƶ�
			boolean canLeft = true;
			boolean canRight = true;
			
			for(int i=0;i<bg.getObstacleList().size();i++) {
				Obstacle ob1 = bg.getObstacleList().get(i);
				//�ж��Ƿ��������
				if(ob1.getX() == this.x+36 && (ob1.getY()+65>this.y && ob1.getY()-35<this.y)) {
					canRight = false;
				}
				
				//�ж��Ƿ��������
				if(ob1.getX() == this.x-36 && (ob1.getY()+65>this.y && ob1.getY()-35<this.y)) {
					canLeft = false;
				}
			}
			
			if(face_to && !canLeft ||this.x == 0) {//��ʱĢ����������������ϰ�����ߵ�����Ļ�����
				//��ʱ��תĢ���ֵķ���ʹ֮������
				face_to = false;
			}else if((!face_to) && (!canRight) ||this.x == 764) {//��ʱĢ�������Ҳ��������ϰ�����ߵ�����Ļ���Ҳ�
				//��ʱ��תĢ���ֵķ���ʹ֮������
				face_to = true;
			}
			
			//�жϵ����Ƿ�Ϊʳ�˻�
			if(type == 2) {
				if(face_to) {//��ʱ�����ƶ�
					this.y -= 2;
				}else {//��ʱ�����ƶ�
					this.y += 2;
				}
				
				image_type = image_type == 1 ? 0 : 1 ;
				
				//�ж�ʳ�˻��Ƿ񵽴Ｋ��λ��
				if(face_to && (this.y == max_up)) {
					face_to = false;
				}
				if((!face_to) && (this.y == max_down)) {
					face_to = true;
				}
				
				show = StaticValue.flower.get(image_type);
			}
			
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
