package com.sxt;

import java.awt.image.BufferedImage;

public class Obstacle implements Runnable{
	//����
	private int x;
	private int y;
	//��¼�ϰ�������
	private int type;
	//��ʾͼ��
	private BufferedImage show = null;
	//���嵱ǰ��������
	private BackGround bg = null;
	//�������ڱ�ʾ���ӽ�����̵��̶߳���
	private Thread thread = new Thread(this);
	
	public Obstacle(int x,int y,int type,BackGround bg) {
		this.setX(x);
		this.setY(y);
		this.setType(type);
		this.bg = bg;
		setShow(StaticValue.obstacle.get(type));
		//���ϰ���Ϊ���ӣ������߳�
		if(type == 8) {
			thread.start();
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BufferedImage getShow() {
		return show;
	}

	public void setShow(BufferedImage show) {
		this.show = show;
	}

	@Override
	public void run() {
		while(true) {
			if(this.bg.isReach()) {//�ж������Ƿ񵽴����λ��
				if(this.y<374) {//������δ��أ����������
					this.y += 5;
				}else {
					this.bg.setBase(true);//�������
				}
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
