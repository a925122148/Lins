package com.sxt;

import java.awt.image.BufferedImage;

public class Kunrio implements Runnable{
	
	//���ڱ�ʾ������
	private int x;
	
	//���ڱ�ʾ������
	private int y;
	
	//���ڱ�ʾ��ǰ״̬
	private String status;
	
	//������ʾ��ǰ״̬����Ӧ��ͼ��
	private BufferedImage show = null;
	
	//����һ��BackGround�������ڻ�ȡ�ϰ�����Ϣ
	private BackGround backGround = new BackGround();
	
	//����ʵ����ҵĶ���
	private Thread thread = null;
	
	//�������ƶ��ٶ�
	private int xSpeed;
	
	//��������Ծ�ٶ�
	private int ySpeed;
	
	//����һ������
	private int index;
	
	//��ʾ�������ڿ��е�ʱ��
	private int upTime = 0;
	
	//�ж������Ƿ�λ�ڳǱ��ſ�
	private boolean isOK;
	
	//�ж������Ƿ�ʧ��
	private boolean isDeath = false;
	
	//��ʾ����,�ƻ�ש���1�֣�������˵�2��
	private int score = 0;
	
	//�ղι���
	public Kunrio() {
		
	}
	
	public Kunrio(int x,int y) {
		this.setX(x);
		this.setY(y);
		setShow(StaticValue.stand_R);
		this.status="stand--right";
		thread = new Thread(this);
		thread.start();
	}

	//����ʧ��
	public void death() {
		isDeath = true;
	}
	
	//���������ƶ�
	public void leftMove() {
		//�趨�ٶ�
		xSpeed = -5;
		//�ж������Ƿ���������,�������޷��ƶ�
		if(backGround.isReach()) {
			xSpeed=0;
		}
		//�ж������Ƿ��ڿ���
		if(status.indexOf("jump")!=-1) {
			status = "jump--left";
		}else {
			status = "move--left";
		}
	}
	
	//���������ƶ�
	public void rightMove() {
		//�趨�ٶ�
		xSpeed = 5;
		//�ж������Ƿ���������,�������޷��ƶ�
		if(backGround.isReach()) {
			xSpeed=0;
		}
		//�ж������Ƿ��ڿ���
		if(status.indexOf("jump")!=-1) {
			status = "jump--right";
		}else {
			status = "move--right";
		}
	}
	
	//��������ֹͣ
	public void leftStop() {
		//�ı��ٶ�
		xSpeed = 0;
		//�ж������Ƿ��ڿ���
		if(status.indexOf("jump")!=-1) {
			status = "jump--left";
		}else {
			status = "stop--left";
		}
	}	
	
	//��������ֹͣ
		public void rightStop() {
			//�ı��ٶ�
			xSpeed = 0;
			//�ж������Ƿ��ڿ���
			if(status.indexOf("jump")!=-1) {
				status = "jump--right";
			}else {
				status = "stop--right";
			}
		}
		
	//������Ծ
		public void jump() {
			if(status.indexOf("jump")==-1) {//����-1ʱ��ʾ�������ڿ���
				if(status.indexOf("left")!=-1) {//����Ϊ��
					status = "jump--left";
				}else {//����Ϊ��
					status = "jump--right";
				}				
				//��ʱ������Ծ�߶�Ϊ70
				ySpeed = -10;
				upTime = 7;
			}
			
			//�ж������Ƿ���������,�������޷��ƶ�
			if(backGround.isReach()) {
				ySpeed = 0;
			}
		}
	//��������
		public void fall() {
				if(status.indexOf("left")!=-1) {//����Ϊ��
					status = "jump--left";
				}else {//����Ϊ��
					status = "jump--right";
				}				
				//��ʱ���������ٶ�Ϊ10
				ySpeed = 10;
			}
	
	@Override
	public void run() {
		while(true) {
			//�ж��Ƿ����ϰ����ϣ��ó�ʼֵΪ��
			boolean onObstacle = false;
			
			//�ж��Ƿ���������ߣ��ó�ʼֵΪ��
			boolean canRight = true;
				
			//�ж��Ƿ���������ߣ��ó�ʼֵΪ��
			boolean canLeft = true;
			
			//�ж������Ƿ񵽴����λ��,����������������һͬ�������������߽��Ǳ�,���������������
			//��˵ĺ�����Ϊ500
			if(backGround.isFlag() && this.x>=500) {
				this.backGround.setReach(true);
				
				//�ж������Ƿ����������,�������������Ǳ��ƶ�
				if(this.backGround.isBase()) {
					status = "move--right";
					if(x < 680) {//������δ�ߵ��Ǳ��м䣬�����ǰ��
						x += 5;
					}else {//�������ߵ��Ǳ��м�
						isOK = true;
					}
				}else {//��ʱ����δ�������,���������������ٶ���Ϊ0�������������
					if(y < 395) {
						xSpeed = 0;
						this.y+=5;
						status = "jump--right";
					}
					
					if(y > 395) {//��ʱ�����������
						this.y = 395;
						status = "stop--right";
					}
				}
				
			}else {
				
			//������ǰ�����������ϰ���
			for(int i=0;i<backGround.getObstacleList().size();i++) {
				Obstacle ob = backGround.getObstacleList().get(i);
				//�ж������Ƿ�λ���ϰ�����
				
				//getY()��getX()�ֱ�Ϊ�������������������
				//this.x��this.y�ֱ�Ϊ�ϰ��ĺ�������������
				if(ob.getY()==this.y+25 && (ob.getX()>this.x-30 && ob.getX()<this.x+25)) {
					onObstacle = true;
				}
				
				//�ж��Ƿ�����������ש��
				if((ob.getY()>=this.y-30 && ob.getY()<=this.y-20) && (ob.getX()>this.x-30 && ob.getX()<this.x+25)) {
					if(ob.getType()==0) {//��������ש��Ϊ��ͨש��
						backGround.getObstacleList().remove(ob);
						score += 1;
					}
					upTime = 0;//�����ϰ���ʱ��������
				}
				
				//�ж��Ƿ����������
				if(ob.getX()==this.x+25 && (ob.getY()>this.y-30 && ob.getY()<this.y+25)) {
					canRight = false;//��ʱ�Ҳ����ϰ���
				}
				
				//�ж��Ƿ����������
				if(ob.getX()==this.x-30 && (ob.getY()>this.y-30 && ob.getY()<this.y+25)) {
					canLeft = false;//��ʱ������ϰ���
				}
				
			}
			
			//�ж������Ƿ���������ʧ�ܻ����Ģ����
			for(int i=0;i<backGround.getEnemyList().size();i++) {
				Enemy e = backGround.getEnemyList().get(i);
				
				if(e.getY() == this.y+20 && (e.getX()-25 <= this.x && e.getX()+36 >= this.x)) {//��ʱ����λ�ڵ���ͷ��
						if(e.getType() == 1) {//��ʱ����ΪĢ���֣���������ʧ��
							e.death();
							score += 2;
							upTime = 3;
							ySpeed = -10;
						}else if(e.getType() == 2) {//��ʱ����Ϊʳ�˻�������ʧ��
							//��ʱ����λ��ʳ�˻�ͷ�ϣ�ʧ��
							death();
						}
				}
				if((e.getX()+35>this.x && e.getX()-25<this.x) && (e.getY()+35>this.y && e.getY()-20<this.y)) {//�ж������Ƿ�������
					//��ʱ�������������ˣ�ʧ��
					death();
				}
			}
			
			//������Ծ����
			//�ж�����ı��Ƿ�λ���ϰ����Ͻ���
			if(onObstacle && upTime == 0) {//��ʱ����δ������Ծ����
				if(status.indexOf("left")!=-1) {//�ж��Ƿ��������
					if(xSpeed!=0) {//�ж��Ƿ����ƶ������ǣ����ʾ��ʱ�����������ƶ�
						status="move--left";
					}else {//�������ʾ�������ֹͣ����
						status="stop--left";
					}
					
				}else {//��ʱ���������ұ�
					if(xSpeed!=0) {//�ж��Ƿ����ƶ������ǣ����ʾ��ʱ�����������ƶ�
						status="move--right";
					}else {
						status="stop--right";//�������ʾ�����ұ�ֹͣ����
					}
				}
			}else {//��ʱ���������ڸ���״̬
				if(upTime != 0) {//��ʱΪ��Ծ״̬
					upTime--;//��Ծ״̬����ʱ��ݼ�
				}else {//��ʱΪ�½�״̬
					fall();
				}
			y+=ySpeed;//���������������¸ı�
			}
		}	
			if((canLeft && xSpeed<0)||(canRight && xSpeed>0)) {
				x+=xSpeed;//������������ҷ��������ٶȲ�Ϊ0
				//�ж������Ƿ�������ߣ������򲻿��������ƶ����ú�����Ϊ0
				if(x<0) {
					x=0;
				}
			}
			//�ж��Ƿ����ƶ�״̬
			if(status.contains("move")) {
				index = index == 0 ? 1 : 0;
			}
			
            //�ж��Ƿ������ƶ�
            if ("move--left".equals(status)) {
                show = StaticValue.run_L.get(index);
            }
			
            //�ж��Ƿ������ƶ�
            if ("move--right".equals(status)) {
                show = StaticValue.run_R.get(index);
            }
			
			//�ж��Ƿ�����ֹͣ
			if("stop--left".equals(status)) {
				show = StaticValue.stand_L;
			}
			
			//�ж��Ƿ�����ֹͣ
			if("stop--right".equals(status)) {
				show = StaticValue.stand_R;
			}
			
			//�ж��Ƿ�������Ծ
			if("jump--left".equals(status)) {
				show = StaticValue.jump_L;
			}
			
			//�ж��Ƿ�������Ծ
			if("jump--right".equals(status)) {
				show = StaticValue.jump_R;
			}
			
			//ʹ�߳�����50����
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

	public BufferedImage getShow() {
		return show;
	}

	public void setShow(BufferedImage show) {
		this.show = show;
	}

	public BackGround getBackGround() {
		return backGround;
	}

	public void setBackGround(BackGround backGround) {
		this.backGround = backGround;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public boolean isDeath() {
		return isDeath;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
