#include <iostream>
#include <fstream>
#include <string>
#include <iomanip>
#include <vector>
#include <stdlib.h>
#include <string.h>
using namespace std;

//构建学生类
class Student
{
public:
	Student()
	{
		memset(s_num, 0, sizeof(s_num));
		memset(s_name,0, sizeof(s_name));
		memset(s_name, 0, sizeof(college, 0, sizeof(college)));
		borrow_max = 0;
		borrow_quantity = 0;
		memset(borrow_books, 0, sizeof(borrow_books));
	}
	char s_num[15];						//学号
	char s_name[10];					//姓名
	char college[30];					//院系
	int borrow_max;						//最大借阅数量
	int borrow_quantity;				//借阅数量
	char borrow_books[10][30];			//借阅图书

	bool S_SetInto();											//设置学生信息
	friend istream& operator>>(istream& in, Student& cp);		//提取运算符重载
	friend ostream& operator<<(ostream& out, Student& cp);		//插入运算符重载
	bool S_If_match(char p[30]);								//判断学号是否匹配
	void s_display();											//显示学生信息
};

//设置学生信息
bool Student::
    S_SetInto()
{
	char temp[15];
	cout << "请输入学号:(输入+退出)";
	cin >> temp;
	if (temp[0] == '+')
	{
		return false;
	}
	strcpy(s_num, temp);
	cout << "学生姓名:";
	cin >> s_name;
	cout << "院系:";
	cin >> college;
	do
	{
		cout << "最大借阅数量(1-10):";
		cin >> borrow_max;
	}
	while (borrow_max <= 0 || borrow_max > 10);
	return true;
}

//提取运算符重载
istream& operator>>(istream& in, Student& cp)
{
	in >> cp.s_num >> cp.s_name >> cp.college >> cp.borrow_max >> cp.borrow_quantity;
	for (int i = 0; i < cp.borrow_quantity; i++)
	{
		in >> cp.borrow_books[i];
	}
	return in;
}

//插入运算符重载
ostream& operator<<(ostream& out, Student& cp)
{
	out << cp.s_num << ' ' << cp.s_name << ' ' << cp.college << ' ' << cp.borrow_max << ' ' << cp.borrow_quantity << ' ';
	for (int i = 0; i < 10; i++)
	{
		out << cp.borrow_books[i] << ' ';
		if (i == 9)
		{
			out << '\n';
		}
	}
	return out;
}

//判断学号是否匹配
bool Student::S_If_match(char p[30])
{
	return (!strcmp(s_num, p)||!strcmp(s_name, p));
}

//显示学生信息
void Student::s_display()										//显示
{
	cout << setiosflags(ios::left) << "学号:" << setw(12) << s_num << "    " << setw(10) << s_name << "    " << setw(25) << college << endl
		<< "最大借阅量" << borrow_max << endl;
}

//构建图书类
class Book
{
public:
	char b_num[15];					//图书号
	char b_name[30];				//书名
	char author[20];				//作者
	char p_house[30];				//出版社
	int b_quantity;					//图书数量

	bool B_SetInto();										//设置图书信息
	friend istream& operator>>(istream& in, Book& cp);		//提取运算符重载
	friend ostream& operator<<(ostream& out, Book& cp);		//插入运算符重载
	bool B_If_match(char p[30]);
	void b_display();										//图书信息显示
};

//设置图书信息
bool Book::B_SetInto()
{
	char temp[15];
	cout << "请输入图书号:(输入+退出)";
	cin >> temp;
	if (temp[0] == '+')
		return false;
	strcpy(b_num, temp);
	cout << "书名:";
	cin >> b_name;
	cout << "作者:";
	cin >> author;
	cout << "出版社:";
	cin >> p_house;
	cout << "数量:";
	cin >> b_quantity;
	return true;
}

//提取运算符重载
istream& operator>>(istream& in, Book& cp)
{
	in >> cp.b_num >> cp.b_name >> cp.author >> cp.p_house >> cp.b_quantity;
	return in;
}

//插入运算符重载
ostream& operator<<(ostream& out, Book& cp)
{
	out << cp.b_num <<' '<< cp.b_name <<' '<< cp.author <<' '<< cp.p_house <<' '<< cp.b_quantity<<'\n';
	return out;
}

//判断图书号是否匹配
bool Book::B_If_match(char p[30])
{
		return (!strcmp(b_num, p)|| !strcmp(b_name, p));
}

//显示函数
void Book:: b_display()
{
	cout << setiosflags(ios::left) << setw(12) << b_num << "    " << setw(30) << b_name << "    " << setw(10) << author << endl
		<< setw(20) << p_house << "    " <<"剩余数量:"<< setw(3) << b_quantity << endl;
}

//构建管理类
class management
{
public:
	int s_sum=0;												//学生数
	int b_sum=0;												//图书数
	string key;													//管理员密码
	vector<Student> s_array;									//记录学生类
	vector<Book> b_array;										//记录图书类

	void S_clear();												//清理已有学生信息
	void B_clear();												//清理已有图书信息
	void S_Read_file();											//读取学生文件
	void S_Save_file();											//保存学生文件
	void B_Read_file();											//读取图书文件
	void B_Save_file();											//保存图书文件

	bool Student_add();											//添加学生信息
	bool Student_mod();											//修改学生信息
	bool Student_del();											//删除学生信息
	bool Student_scan();										//查看学生信息

	bool Book_add();											//添加图书信息
	bool Book_mod();											//修改图书信息
	bool Book_del();											//删除图书信息
	bool Book_scan();											//查看图书信息

	bool s_login(Student& cp);									//学生凭学号登录
	bool borrow_scan(Student &cp);								//查看借阅数目
	bool borrow_book(Student &cp);								//借书
	bool return_book(Student &cp);								//还书

	bool Student_System();										//学生登录
	bool Personnel_System();									//工作人员登录
	void login_init();											//登录界面初始化
};

//清理已有学生信息
void management::S_clear()
{
	s_array.clear();
	s_sum = 0;
}

//清理已有图书信息
void management::B_clear()
{
	b_array.clear();
	b_sum = 0;
}

//读取学生文件
void management::S_Read_file()
{
	ifstream s_file;
	s_file.open("Student_Information.txt");
	if (!s_file.is_open())
	{
		cerr << "文件\"Student_Information.txt\"无法打开\n";
		exit(1);
	}
	while (!(s_file.eof()))
	{
		Student stu;
		s_file >> stu;
		s_array.push_back(stu);
		s_sum++;
	}
	s_sum--;
	s_file.close();
}

//保存学生文件
void management::S_Save_file()
{
	ofstream s_file("Student_Information.txt");
	if (!s_file)
	{
		cerr << "文件\"Student_Information.txt\"无法打开!\n";
		exit(1);
	}
	int i = -1;
	while (++i < s_sum)
	{
		s_file << s_array[i];
	}
	s_file.close();
}

//读取图书文件
void management::B_Read_file()
{
	ifstream b_file;
	b_file.open("Book_Information.txt");
	if (!b_file.is_open())
	{
		cerr << "文件\"Book_Information.txt\"无法打开\n";
		exit(1);
	}
	while (!b_file.eof())
	{
		Book _book;
		b_file >> _book;
		b_array.push_back(_book);
		b_sum++;
	};
	b_sum--;
	b_file.close();
}

//保存图书文件
void management::B_Save_file()
{
	ofstream b_file("Book_Information.txt");
	if (!b_file)
	{
		cerr << "文件\"Book_Information.txt\"无法打开!\n";
		exit(1);
	}
	int i = -1;
	while (++i < b_sum)
	{
		b_file << b_array[i];
	}
	b_file.close();
}

//添加学生信息
bool management::Student_add()
{
	S_Read_file();
	Student _stu;
	cout << "请进行信息录入。按“+”结束!\n";
	do
	{
		s_array.push_back(_stu);
	}
	while (s_array[s_sum++].S_SetInto());
	s_sum--;
	s_array.pop_back();
	return true;
}

//修改学生信息
bool management::Student_mod()
{
    int flag=0;
	char _s_num[15];
	S_Read_file();
	cout << "请输入您要修改的学生信息的学号或名字:";
	cin >> _s_num;
	int i = 0;
	for (; i < s_sum; i++)
	{
		if (s_array[i].S_If_match(_s_num))
		{
		    flag=1;
			cout << "该同学的原信息为:\n";
			s_array[i].s_display();
			cout << "请输入正确信息: \n";
			s_array[i].S_SetInto();
			s_sum++;							//保持学生数
			return true;
		}

	}
	if (flag == 0)
		{
			cout << "抱歉!您要修改的信息不存在! " << endl;
			return false;
		}
}

//删除学生信息
bool management::Student_del()
{
	char _s_num[15];
	S_Read_file();
	cout << "请输入您要删除的学生信息的学号:";
	cin >> _s_num;
	for (int i = 0; i < s_sum; i++)
	{
		if (s_array[i].S_If_match(_s_num))
		{
			cout << "该同学的原信息为:\n";
			s_array[i].s_display();
			s_array.erase(s_array.begin() + i);
			s_sum--;
			return true;
		}
		if (i == s_sum-1)
		{
			cout << "抱歉!您要删除的信息不存在! " << endl;
			return false;
		}
	}
}

//查看学生信息
bool management::Student_scan()
{
	S_Read_file();
	if (s_sum == 0)
	{
		cout << "暂无信息!请等待管理人员更新!";
		return false;
	}
	for (int i = 0; i < s_sum; i++)
	{
		s_array[i].s_display();
	}
	return true;
}

//添加图书信息
bool management::Book_add()
{
	B_Read_file();
	Book _book;
	cout << "请进行信息录入。按“+”结束!\n";
	do
	{
		b_array.push_back(_book);
	} while (b_array[b_sum++].B_SetInto());
	b_sum--;
	b_array.pop_back();
	return true;
}

//修改图书信息
bool management::Book_mod()
{
    int flag=0;
	char _b_num[30];
	B_Read_file();
	cout << "请输入您要修改的图书信息的图书号或书名:";
	cin >> _b_num;
	for (int i = 0; i < b_sum; i++)
	{
		if (b_array[i].B_If_match(_b_num))
		{
		    flag=1;
			cout << "该图书的原信息为:\n";
			b_array[i].b_display();
			cout << "请输入正确信息！ \n";
			b_array[i].B_SetInto();
			//b_sum++;							//保持总航线数不变
			return true;
		}


	}
	if (flag==0)
		{
			cout << "抱歉!您要修改的信息不存在! " << endl;
			return false;
		}
}

//删除图书信息
bool management::Book_del()
{
	char _b_num[15];
	B_Read_file();
	cout << "请输入您要删除的图书信息的图书号:";
	cin >> _b_num;
	for (int i = 0; i < b_sum; i++)
	{
		if (b_array[i].B_If_match(_b_num))
		{
			cout << "该图书的原信息为:\n";
			b_array[i].b_display();
			b_array.erase(b_array.begin() + i);
			b_sum--;
			return true;
		}
		if (i == b_sum-1)
		{
			cout << "抱歉!您要删除的信息不存在! " << endl;
			return false;
		}
	}
}

//查看图书信息
bool management::Book_scan()
{
	B_Read_file();
	if (b_sum == 0)
	{
		cout << "暂无信息!请等待管理人员更新!";
		return false;
	}
	for (int i = 0; i < b_sum; i++)
	{
		b_array[i].b_display();
	}
	return true;
}

//学生凭学号登录
bool management::s_login(Student& cp)
{
	char _s_num[15];
	S_Read_file();
	cout << "请输入您的学号:";
	cin >> _s_num;
	for (int i = 0; i < s_sum; i++)
	{
		if (s_array[i].S_If_match(_s_num))
		{
			cp=s_array[i];
			cout << "欢迎您," << cp.s_name << "同学！" << endl;
			S_clear();
			return true;
		}
	}
	S_clear();
	return false;
}

//查看借阅数数目
bool management::borrow_scan(Student& cp)
{
	S_Read_file();
	B_Read_file();
	cout << "您已借阅图书" << setw(3) << cp.borrow_quantity << "本" << endl;
	for (int i = 0; i < cp.borrow_quantity; i++)
	{
		cout << cp.borrow_books[i] << endl;
	}
	S_clear();
	B_clear();
	return true;
}

//借书
bool management::borrow_book(Student& cp)
{
	S_Read_file();
	B_Read_file();
	char _b_num[30];
	cout << "请输入想借图书的图书号或书名:";
	cin >> _b_num;
	for (int i = 0; i < b_sum; i++)
	{
	    if(b_array[i].b_quantity<=0)
        {
            cout<<"该图书已被借空，请重新输入借取书籍编号!"<<endl;
            break;
        }
		if (b_array[i].B_If_match(_b_num))
		{
			cout << "该图书的信息为:\n";
			b_array[i].b_display();
			b_array[i].b_quantity--;
			if (cp.borrow_quantity > cp.borrow_max - 1)
			{
				cout << "抱歉，您已达借书最大上限！" << endl;
				return false;
			}
			strcpy(cp.borrow_books[cp.borrow_quantity++],b_array[i].b_name);
			for (int j = 0; j < s_sum; j++)
			{
				if (s_array[j].S_If_match(cp.s_num))
				{
					s_array[j]=cp;
					return true;
				}
			}
		}
		if (i == b_sum - 1)
		{
			cout << "抱歉!您想借的图书未收录! " << endl;
			return false;
		}
	}

}

//还书
bool management::return_book(Student& cp)
{
	S_Read_file();
	B_Read_file();
	char _b_num[30];
	int z=0;
	cout << "请输入想还图书的图书号或书名:";
	cin >> _b_num;
	for (int i = 0; i < b_sum; i++)
	{
		if (b_array[i].B_If_match(_b_num))
		{
			cout << "该图书的信息为:\n";
			b_array[i].b_display();
			//b_array[i].b_quantity++;
			for (int k = 0; k < cp.borrow_quantity; k++)
			{
				if (!strcmp(cp.borrow_books[k],b_array[i].b_name))
				{
					for (int m = k; m < cp.borrow_quantity-1; m++)
					{
						strcpy(cp.borrow_books[m], cp.borrow_books[m+1]);
					}
					strcpy(cp.borrow_books[--cp.borrow_quantity], "");
//					cp.borrow_quantity--;
                    b_array[i].b_quantity++;
					break;
				}
				else
                    {
                        cout<<"您未借阅此书"<<endl;
                        z=1;
                        break;
                    }
			}

			for (int j = 0; j < s_sum; j++)
			{

				if (s_array[j].S_If_match(cp.s_num))
				{
					s_array[j] = cp;
					return true;
				}
			}
		}
		if (i == b_sum)
		{
			cout << "抱歉!您想还的图书未收录! " << endl;
			return false;
		}
	}
}

//工作人员登录
bool management::Personnel_System()
{

	while (1)
	{
	    char temp;
		int menu_options;
		cout << "请输入登录密码:";
		cin >> key;
			if(key!="20210622"){
			cout << "密码错误!" << endl;
            cout << "请重新输入密码或输入“+”号退出:)";
            cin>>temp;
            if (temp== '+')
            {
                return false;
            }
			}
		 else if(key == "20210622")
         {//登录密码
			while (1)
			{
                cout<<"                     ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n";
                cout<<"                     ┃               功能选择              ┃\n";
                cout<<"                     ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                     ┃           1.添加学生信息            ┃\n";
                cout<<"                     ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                     ┃           2.修改学生信息            ┃\n";
                cout<<"                     ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                     ┃           3.删除学生信息            ┃\n";
                cout<<"                     ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                     ┃           4.查看学生信息            ┃\n";
                cout<<"                     ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                     ┃           5.添加图书信息            ┃\n";
                cout<<"                     ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                     ┃           6.修改图书信息            ┃\n";
                cout<<"                     ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                     ┃           7.删除图书信息            ┃\n";
                cout<<"                     ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                     ┃           8.查看图书信息            ┃\n";
                cout<<"                     ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                     ┃           9.退出登录                ┃\n";
                cout<<"                     ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";
				cout << endl;
				cin >> menu_options;
				switch (menu_options)
				{
				case 1:Student_add(); break;
				case 2:Student_mod(); break;
				case 3:Student_del(); break;
				case 4:Student_scan(); break;
				case 5:Book_add(); break;
				case 6:Book_mod(); break;
				case 7:Book_del(); break;
				case 8:Book_scan(); break;
				case 9:return false;
				default:cout << "输入错误,请重新选择" << endl; break;
				}
				if (!(menu_options == 4 || menu_options == 8))
				{
					cout << "是否确认操作？        (Y/y)  or (N/n)" << endl;
					char yn;
					do
					{
						cin >> yn;
					} while (!(yn == 'Y' || yn == 'y' || yn == 'N' || yn == 'n'));
					if (yn == 'Y' || yn == 'y')
					{
						if (menu_options == 1 || menu_options == 2 || menu_options == 3)
						{
							S_Save_file();
						}
						else if (menu_options == 5 || menu_options == 6 || menu_options == 7)
						{
							B_Save_file();
						}
						cout << "操作成功"<<endl;
					}
				}
			}
				S_clear();
				B_clear();
			}
    return true;
}
}

//学生登录
bool management::Student_System()
{
	while (1)
	{
		Student cp;
		bool key = s_login(cp);

		while (key)
		{
			int menu_options;
			cout<< "*****	     主菜单:	      ****" << endl;
			cout<< "*****	      学生:	      ****" << endl;
            cout<< "*****	 1——查看借阅数目    ****" << endl;
            cout<< "*****	 2——借阅图书        ****" << endl;
            cout<< "*****	 3——归还图书        ****"<< endl;
            cout<< "*****    4——查看图书        ****"<< endl;
            cout<< "*****    0——退出登录        ****" << endl;
			cin >> menu_options;
			switch (menu_options)
			{
			case 1:borrow_scan(cp);
			break;
			case 2:borrow_book(cp);
			break;
			case 3:return_book(cp);
			break;
			case 4:Book_scan();
            break;
			case 0:
            return false;
			}
			if (menu_options == 2 || menu_options == 3)
			{
				cout << "是否确认操作？        (Y/y)  or (N/n)" << endl;
				char yn;
				do
				{
					cin >> yn;
				} while (!(yn == 'Y' || yn == 'y' || yn == 'N' || yn == 'n'));
				if (yn == 'Y' || yn == 'y')
				{
					cout << "操作成功！" << endl;
					S_Save_file();
					B_Save_file();
				}
			}
			S_clear();
			B_clear();
		}
		cout << "未找到您的信息！" << endl;
	}
	return true;
}

//界面初始化函数
void management::login_init()
{
	system("cls");
                cout<<"                            ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n";
                cout<<"                            ┃>>>>>>>>>欢迎进入图书管理系统<<<<<<<<┃\n";
                cout<<"                            ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                            ┃             1.管理员                ┃\n";
                cout<<"                            ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                            ┃             2.学生                  ┃\n";
                cout<<"                            ┃━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┃\n";
                cout<<"                            ┃             3.退出系统              ┃\n";
                cout<<"                            ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";
}

//主函数
int main()
{
	management xiangnan;
	//若文件不存在，则新建文件
	//存放学生信息
	ofstream Student_Information("Student_Information.txt", ios::app);
	if (!Student_Information)
	{
		cerr << "文件\"flight information.dat\"无法打开!\n";
		exit(1);
	}
	Student_Information.close();

	//存放图书信息
	ofstream Book_Information("Book_Information.txt", ios::app);
	if (!Book_Information)
	{
		cerr << "文件\"flight information.dat\"无法打开!\n";
		exit(1);
	}
	Book_Information.close();

	int dlry;										//登陆人员
	while (1)
	{
		xiangnan.login_init();							//界面初始化
		cin >> dlry;
		if (dlry == 1)
		{
			xiangnan.Personnel_System();
		}
		else if (dlry == 2)
		{
			xiangnan.Student_System();
		}
		else if (dlry == 3)
		{
			return 0;
		}
		else
		{
			cout << "输入错误，请重新选择!" << endl;
		}
	}
	return 0;
}
