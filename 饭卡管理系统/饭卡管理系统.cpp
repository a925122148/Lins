#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define MAX 5
struct fanka{
	char number[20];
	char name[20];
	int zhuangtai;
	float yue;
}fanka[MAX];
int count=0;
void jianli();
void maifan();
void xuqian();
void tianjia();
void zhuxiao();
void guashi();
void guashu();
int main()
{int choose;
do {
    system("cls");
	printf("===================��������ϵͳ==================\n");
	printf("\t\t1.���������ļ�\n");
	printf("\t\t2.��\n");
	printf("\t\t3.��Ǯ\n");
	printf("\t\t4.����·���\n");
	printf("\t\t5.ע���ɷ���\n");
	printf("\t\t6.����������ʧ\n");
	printf("\t\t7.ͳ�ƹ�ʧ��������\n");
	printf("\t\t0.�˳�����\n\n");

	fflush(stdin);

	printf("�����빦��ѡ��:");
	scanf("%d",&choose);
	switch(choose)
	{
	case 1:jianli();break;
	case 2:maifan();break;
	case 3:xuqian();break;
	case 4:tianjia();break;
	case 5:zhuxiao();break;
	case 6:guashi();break;
	case 7:guashu();break;
	case 0:printf("��лʹ�ñ�������������˳����������������\n");break;}
}while(choose!=0);
return 0;
}
void jianli()
{
	char a;
	do
	{
		fflush(stdin);
		printf("\n�����뿨��:");
		gets(fanka[count].number);
		fflush(stdin);
		printf("\n����������:");
		gets(fanka[count].name);
		fflush(stdin);
        fanka[count].zhuangtai=0;
		fflush(stdin);
		printf("\n�����뷹�����:");
		scanf("%f",&fanka[count].yue);
		fflush(stdin);
		printf("     ������ɣ�    ");
		printf("�Ƿ����?(y/n)?");
		a=getchar();
		count++;
	}while((a=='y')&&(count<=MAX));
}
void guashi()
{
	char temp[20];
	int b,c;

		fflush(stdin);

	printf("�����뿨��:");
	gets(temp);
	for(c=0;c<count;c++)
	{
       if(strcmp(temp,fanka[c].number)==0)
	   break;

	}
    		fflush(stdin);
	printf("\n��ѡ���ܣ�1.��ʧ 2.�����ʧ (1 or 2)?");
	scanf("%d",&b);

	if(b==1)
	{
		fanka[c].zhuangtai=1;
		printf("��ʧ�ɹ���\n");
	}
	if(b==2)
	{
		fanka[c].zhuangtai=0;
		printf("�����ʧ�ɹ���");
	}

}
void zhuxiao()
{
	char number[20],c;
	int a,b,i;
	do
	{
        fflush(stdin);
		printf("������Ҫɾ���Ŀ��ţ�");
		gets(number);
		for(a=0;a<count;a++)
		{
			if(strcmp(number,fanka[a].number)==0)break;
		}
			for(b=a;b<count;b++)
				fanka[b]=fanka[b+1];
			if(a>count)printf("δ�ҵ�������ķ�����");
			else {
			count--;
			printf("ʣ��ķ���Ϊ��\n");
			for(i=0;i<count;i++)

				printf("%s   %s\n",fanka[i].number,fanka[i].name);
			}
						  fflush(stdin);
			printf("�Ƿ����?(y/n)");

             c=getchar();
	}      while(c=='y');
}
void tianjia()
{
	int i;
	char c;
	do
	{
		fflush(stdin);
		printf("\n�����뿨��:");
		gets(fanka[count].number);
		fflush(stdin);
		printf("\n����������:");
		gets(fanka[count].name);
		fflush(stdin);
		printf("\n�����뷹��״̬���ѹ�ʧ����1��δ��ʧ����0:");
		scanf("%d",&fanka[count].zhuangtai);
		fflush(stdin);
		printf("\n�����뷹�����:");
		scanf("%f",&fanka[count].yue);

        for(i=0;i<count;i++)
		     if(strcmp(fanka[count].number,fanka[i].number)==0)
			 {printf("�ÿ����ѱ�ע�ᣬ���������룡\n");
		    	break;
			 }
	    if(i==count)
		{printf("ע��ɹ�\n");
		count=count+1;
		}
        printf("�Ƿ��������y/n��\n");
        fflush(stdin);
		c=getchar();
	}while(c=='y');
		}
void maifan()
{
	int i;
	float m;
	char a;
	char num[20];
	do
	{
		system("cls");
        fflush(stdin);
        printf("\n�����뷹����:");
	    gets(num);
		fflush(stdin);
	    printf("\n�����뷹��:");
	    scanf("%f",&m);
		if(m<=0)
			printf("\n��������ȷ�ķ���\n");
		else
		{

	     for(i=0;i<count;i++)

		    if(strcmp(num,fanka[i].number)==0)
			break;

         if(i==count)
			printf("δ�ҵ����˻�\n");
		 else if(fanka[i].zhuangtai==1)
			printf("�����Ѷ���\n");
		 else if(fanka[i].yue<5)
			printf("����������Ǯ��\n");
         else
		 {
			printf("\n����ǰ:%5.2f\n",fanka[i].yue);
            fanka[i].yue=fanka[i].yue-m;
			printf("\n���Ѻ�:%5.2f\n",fanka[i].yue);
		 }
		}
		printf("\n�Ƿ����?\n������y��n:");
        fflush(stdin);
		a=getchar();
	}while(a=='y');

}
void xuqian()
{
	int i;
	float m;
	char num[20];
	char a;
	do
	{
		system("cls");
        fflush(stdin);
        printf("\n�����뷹����:");
	    gets(num);
        fflush(stdin);
	    printf("\n��������Ǯ��:");
	    scanf("%f",&m);
		if(m<=0)
			printf("\n��������ȷ�ķ���\n");
		else
		{

	     for(i=0;i<count;i++)
		    if(strcmp(num,fanka[i].number)==0)
			break;

		 if(i==count)
			printf("δ�ҵ����˻���\n");
		 else if(fanka[i].zhuangtai==1)
			printf("�����Ѷ��ᣡ\n");
		 else
		 {
			printf("\n����ǰ:%5.2f\n",fanka[i].yue);
            fanka[i].yue=fanka[i].yue+m;
			printf("\n���Ѻ�:%5.2f\n",fanka[i].yue);
		 }
		}
		printf("\n�Ƿ����?\n������y��n:");
        fflush(stdin);
		a=getchar();
	}while(a=='y');
}
void guashu()
{
	int i,sum;
    for(i=0,sum=0;i<count;i++)
    if(fanka[i].zhuangtai==1)
	{sum=sum+1;
	 printf("\n������:%s\t����:%s\t���:%5.2f\n",fanka[i].number,fanka[i].name,fanka[i].yue);
	}
	printf("\n��ʧ��������:%d\n",sum);
	printf("\n�����������:");
    fflush(stdin);
    getchar();
}
