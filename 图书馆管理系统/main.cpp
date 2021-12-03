#include <iostream>
#include <fstream>
#include <string>
#include <iomanip>
#include <vector>
#include <stdlib.h>
#include <string.h>
using namespace std;

//����ѧ����
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
	char s_num[15];						//ѧ��
	char s_name[10];					//����
	char college[30];					//Ժϵ
	int borrow_max;						//����������
	int borrow_quantity;				//��������
	char borrow_books[10][30];			//����ͼ��

	bool S_SetInto();											//����ѧ����Ϣ
	friend istream& operator>>(istream& in, Student& cp);		//��ȡ���������
	friend ostream& operator<<(ostream& out, Student& cp);		//�������������
	bool S_If_match(char p[30]);								//�ж�ѧ���Ƿ�ƥ��
	void s_display();											//��ʾѧ����Ϣ
};

//����ѧ����Ϣ
bool Student::
    S_SetInto()
{
	char temp[15];
	cout << "������ѧ��:(����+�˳�)";
	cin >> temp;
	if (temp[0] == '+')
	{
		return false;
	}
	strcpy(s_num, temp);
	cout << "ѧ������:";
	cin >> s_name;
	cout << "Ժϵ:";
	cin >> college;
	do
	{
		cout << "����������(1-10):";
		cin >> borrow_max;
	}
	while (borrow_max <= 0 || borrow_max > 10);
	return true;
}

//��ȡ���������
istream& operator>>(istream& in, Student& cp)
{
	in >> cp.s_num >> cp.s_name >> cp.college >> cp.borrow_max >> cp.borrow_quantity;
	for (int i = 0; i < cp.borrow_quantity; i++)
	{
		in >> cp.borrow_books[i];
	}
	return in;
}

//�������������
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

//�ж�ѧ���Ƿ�ƥ��
bool Student::S_If_match(char p[30])
{
	return (!strcmp(s_num, p)||!strcmp(s_name, p));
}

//��ʾѧ����Ϣ
void Student::s_display()										//��ʾ
{
	cout << setiosflags(ios::left) << "ѧ��:" << setw(12) << s_num << "    " << setw(10) << s_name << "    " << setw(25) << college << endl
		<< "��������" << borrow_max << endl;
}

//����ͼ����
class Book
{
public:
	char b_num[15];					//ͼ���
	char b_name[30];				//����
	char author[20];				//����
	char p_house[30];				//������
	int b_quantity;					//ͼ������

	bool B_SetInto();										//����ͼ����Ϣ
	friend istream& operator>>(istream& in, Book& cp);		//��ȡ���������
	friend ostream& operator<<(ostream& out, Book& cp);		//�������������
	bool B_If_match(char p[30]);
	void b_display();										//ͼ����Ϣ��ʾ
};

//����ͼ����Ϣ
bool Book::B_SetInto()
{
	char temp[15];
	cout << "������ͼ���:(����+�˳�)";
	cin >> temp;
	if (temp[0] == '+')
		return false;
	strcpy(b_num, temp);
	cout << "����:";
	cin >> b_name;
	cout << "����:";
	cin >> author;
	cout << "������:";
	cin >> p_house;
	cout << "����:";
	cin >> b_quantity;
	return true;
}

//��ȡ���������
istream& operator>>(istream& in, Book& cp)
{
	in >> cp.b_num >> cp.b_name >> cp.author >> cp.p_house >> cp.b_quantity;
	return in;
}

//�������������
ostream& operator<<(ostream& out, Book& cp)
{
	out << cp.b_num <<' '<< cp.b_name <<' '<< cp.author <<' '<< cp.p_house <<' '<< cp.b_quantity<<'\n';
	return out;
}

//�ж�ͼ����Ƿ�ƥ��
bool Book::B_If_match(char p[30])
{
		return (!strcmp(b_num, p)|| !strcmp(b_name, p));
}

//��ʾ����
void Book:: b_display()
{
	cout << setiosflags(ios::left) << setw(12) << b_num << "    " << setw(30) << b_name << "    " << setw(10) << author << endl
		<< setw(20) << p_house << "    " <<"ʣ������:"<< setw(3) << b_quantity << endl;
}

//����������
class management
{
public:
	int s_sum=0;												//ѧ����
	int b_sum=0;												//ͼ����
	string key;													//����Ա����
	vector<Student> s_array;									//��¼ѧ����
	vector<Book> b_array;										//��¼ͼ����

	void S_clear();												//��������ѧ����Ϣ
	void B_clear();												//��������ͼ����Ϣ
	void S_Read_file();											//��ȡѧ���ļ�
	void S_Save_file();											//����ѧ���ļ�
	void B_Read_file();											//��ȡͼ���ļ�
	void B_Save_file();											//����ͼ���ļ�

	bool Student_add();											//���ѧ����Ϣ
	bool Student_mod();											//�޸�ѧ����Ϣ
	bool Student_del();											//ɾ��ѧ����Ϣ
	bool Student_scan();										//�鿴ѧ����Ϣ

	bool Book_add();											//���ͼ����Ϣ
	bool Book_mod();											//�޸�ͼ����Ϣ
	bool Book_del();											//ɾ��ͼ����Ϣ
	bool Book_scan();											//�鿴ͼ����Ϣ

	bool s_login(Student& cp);									//ѧ��ƾѧ�ŵ�¼
	bool borrow_scan(Student &cp);								//�鿴������Ŀ
	bool borrow_book(Student &cp);								//����
	bool return_book(Student &cp);								//����

	bool Student_System();										//ѧ����¼
	bool Personnel_System();									//������Ա��¼
	void login_init();											//��¼�����ʼ��
};

//��������ѧ����Ϣ
void management::S_clear()
{
	s_array.clear();
	s_sum = 0;
}

//��������ͼ����Ϣ
void management::B_clear()
{
	b_array.clear();
	b_sum = 0;
}

//��ȡѧ���ļ�
void management::S_Read_file()
{
	ifstream s_file;
	s_file.open("Student_Information.txt");
	if (!s_file.is_open())
	{
		cerr << "�ļ�\"Student_Information.txt\"�޷���\n";
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

//����ѧ���ļ�
void management::S_Save_file()
{
	ofstream s_file("Student_Information.txt");
	if (!s_file)
	{
		cerr << "�ļ�\"Student_Information.txt\"�޷���!\n";
		exit(1);
	}
	int i = -1;
	while (++i < s_sum)
	{
		s_file << s_array[i];
	}
	s_file.close();
}

//��ȡͼ���ļ�
void management::B_Read_file()
{
	ifstream b_file;
	b_file.open("Book_Information.txt");
	if (!b_file.is_open())
	{
		cerr << "�ļ�\"Book_Information.txt\"�޷���\n";
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

//����ͼ���ļ�
void management::B_Save_file()
{
	ofstream b_file("Book_Information.txt");
	if (!b_file)
	{
		cerr << "�ļ�\"Book_Information.txt\"�޷���!\n";
		exit(1);
	}
	int i = -1;
	while (++i < b_sum)
	{
		b_file << b_array[i];
	}
	b_file.close();
}

//���ѧ����Ϣ
bool management::Student_add()
{
	S_Read_file();
	Student _stu;
	cout << "�������Ϣ¼�롣����+������!\n";
	do
	{
		s_array.push_back(_stu);
	}
	while (s_array[s_sum++].S_SetInto());
	s_sum--;
	s_array.pop_back();
	return true;
}

//�޸�ѧ����Ϣ
bool management::Student_mod()
{
    int flag=0;
	char _s_num[15];
	S_Read_file();
	cout << "��������Ҫ�޸ĵ�ѧ����Ϣ��ѧ�Ż�����:";
	cin >> _s_num;
	int i = 0;
	for (; i < s_sum; i++)
	{
		if (s_array[i].S_If_match(_s_num))
		{
		    flag=1;
			cout << "��ͬѧ��ԭ��ϢΪ:\n";
			s_array[i].s_display();
			cout << "��������ȷ��Ϣ: \n";
			s_array[i].S_SetInto();
			s_sum++;							//����ѧ����
			return true;
		}

	}
	if (flag == 0)
		{
			cout << "��Ǹ!��Ҫ�޸ĵ���Ϣ������! " << endl;
			return false;
		}
}

//ɾ��ѧ����Ϣ
bool management::Student_del()
{
	char _s_num[15];
	S_Read_file();
	cout << "��������Ҫɾ����ѧ����Ϣ��ѧ��:";
	cin >> _s_num;
	for (int i = 0; i < s_sum; i++)
	{
		if (s_array[i].S_If_match(_s_num))
		{
			cout << "��ͬѧ��ԭ��ϢΪ:\n";
			s_array[i].s_display();
			s_array.erase(s_array.begin() + i);
			s_sum--;
			return true;
		}
		if (i == s_sum-1)
		{
			cout << "��Ǹ!��Ҫɾ������Ϣ������! " << endl;
			return false;
		}
	}
}

//�鿴ѧ����Ϣ
bool management::Student_scan()
{
	S_Read_file();
	if (s_sum == 0)
	{
		cout << "������Ϣ!��ȴ�������Ա����!";
		return false;
	}
	for (int i = 0; i < s_sum; i++)
	{
		s_array[i].s_display();
	}
	return true;
}

//���ͼ����Ϣ
bool management::Book_add()
{
	B_Read_file();
	Book _book;
	cout << "�������Ϣ¼�롣����+������!\n";
	do
	{
		b_array.push_back(_book);
	} while (b_array[b_sum++].B_SetInto());
	b_sum--;
	b_array.pop_back();
	return true;
}

//�޸�ͼ����Ϣ
bool management::Book_mod()
{
    int flag=0;
	char _b_num[30];
	B_Read_file();
	cout << "��������Ҫ�޸ĵ�ͼ����Ϣ��ͼ��Ż�����:";
	cin >> _b_num;
	for (int i = 0; i < b_sum; i++)
	{
		if (b_array[i].B_If_match(_b_num))
		{
		    flag=1;
			cout << "��ͼ���ԭ��ϢΪ:\n";
			b_array[i].b_display();
			cout << "��������ȷ��Ϣ�� \n";
			b_array[i].B_SetInto();
			//b_sum++;							//�����ܺ���������
			return true;
		}


	}
	if (flag==0)
		{
			cout << "��Ǹ!��Ҫ�޸ĵ���Ϣ������! " << endl;
			return false;
		}
}

//ɾ��ͼ����Ϣ
bool management::Book_del()
{
	char _b_num[15];
	B_Read_file();
	cout << "��������Ҫɾ����ͼ����Ϣ��ͼ���:";
	cin >> _b_num;
	for (int i = 0; i < b_sum; i++)
	{
		if (b_array[i].B_If_match(_b_num))
		{
			cout << "��ͼ���ԭ��ϢΪ:\n";
			b_array[i].b_display();
			b_array.erase(b_array.begin() + i);
			b_sum--;
			return true;
		}
		if (i == b_sum-1)
		{
			cout << "��Ǹ!��Ҫɾ������Ϣ������! " << endl;
			return false;
		}
	}
}

//�鿴ͼ����Ϣ
bool management::Book_scan()
{
	B_Read_file();
	if (b_sum == 0)
	{
		cout << "������Ϣ!��ȴ�������Ա����!";
		return false;
	}
	for (int i = 0; i < b_sum; i++)
	{
		b_array[i].b_display();
	}
	return true;
}

//ѧ��ƾѧ�ŵ�¼
bool management::s_login(Student& cp)
{
	char _s_num[15];
	S_Read_file();
	cout << "����������ѧ��:";
	cin >> _s_num;
	for (int i = 0; i < s_sum; i++)
	{
		if (s_array[i].S_If_match(_s_num))
		{
			cp=s_array[i];
			cout << "��ӭ��," << cp.s_name << "ͬѧ��" << endl;
			S_clear();
			return true;
		}
	}
	S_clear();
	return false;
}

//�鿴��������Ŀ
bool management::borrow_scan(Student& cp)
{
	S_Read_file();
	B_Read_file();
	cout << "���ѽ���ͼ��" << setw(3) << cp.borrow_quantity << "��" << endl;
	for (int i = 0; i < cp.borrow_quantity; i++)
	{
		cout << cp.borrow_books[i] << endl;
	}
	S_clear();
	B_clear();
	return true;
}

//����
bool management::borrow_book(Student& cp)
{
	S_Read_file();
	B_Read_file();
	char _b_num[30];
	cout << "���������ͼ���ͼ��Ż�����:";
	cin >> _b_num;
	for (int i = 0; i < b_sum; i++)
	{
	    if(b_array[i].b_quantity<=0)
        {
            cout<<"��ͼ���ѱ���գ������������ȡ�鼮���!"<<endl;
            break;
        }
		if (b_array[i].B_If_match(_b_num))
		{
			cout << "��ͼ�����ϢΪ:\n";
			b_array[i].b_display();
			b_array[i].b_quantity--;
			if (cp.borrow_quantity > cp.borrow_max - 1)
			{
				cout << "��Ǹ�����Ѵ����������ޣ�" << endl;
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
			cout << "��Ǹ!������ͼ��δ��¼! " << endl;
			return false;
		}
	}

}

//����
bool management::return_book(Student& cp)
{
	S_Read_file();
	B_Read_file();
	char _b_num[30];
	int z=0;
	cout << "�������뻹ͼ���ͼ��Ż�����:";
	cin >> _b_num;
	for (int i = 0; i < b_sum; i++)
	{
		if (b_array[i].B_If_match(_b_num))
		{
			cout << "��ͼ�����ϢΪ:\n";
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
                        cout<<"��δ���Ĵ���"<<endl;
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
			cout << "��Ǹ!���뻹��ͼ��δ��¼! " << endl;
			return false;
		}
	}
}

//������Ա��¼
bool management::Personnel_System()
{

	while (1)
	{
	    char temp;
		int menu_options;
		cout << "�������¼����:";
		cin >> key;
			if(key!="20210622"){
			cout << "�������!" << endl;
            cout << "������������������롰+�����˳�:)";
            cin>>temp;
            if (temp== '+')
            {
                return false;
            }
			}
		 else if(key == "20210622")
         {//��¼����
			while (1)
			{
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��               ����ѡ��              ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��           1.���ѧ����Ϣ            ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��           2.�޸�ѧ����Ϣ            ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��           3.ɾ��ѧ����Ϣ            ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��           4.�鿴ѧ����Ϣ            ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��           5.���ͼ����Ϣ            ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��           6.�޸�ͼ����Ϣ            ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��           7.ɾ��ͼ����Ϣ            ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��           8.�鿴ͼ����Ϣ            ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
                cout<<"                     ��           9.�˳���¼                ��\n";
                cout<<"                     ������������������������������������������������������������������������������\n";
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
				default:cout << "�������,������ѡ��" << endl; break;
				}
				if (!(menu_options == 4 || menu_options == 8))
				{
					cout << "�Ƿ�ȷ�ϲ�����        (Y/y)  or (N/n)" << endl;
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
						cout << "�����ɹ�"<<endl;
					}
				}
			}
				S_clear();
				B_clear();
			}
    return true;
}
}

//ѧ����¼
bool management::Student_System()
{
	while (1)
	{
		Student cp;
		bool key = s_login(cp);

		while (key)
		{
			int menu_options;
			cout<< "*****	     ���˵�:	      ****" << endl;
			cout<< "*****	      ѧ��:	      ****" << endl;
            cout<< "*****	 1�����鿴������Ŀ    ****" << endl;
            cout<< "*****	 2��������ͼ��        ****" << endl;
            cout<< "*****	 3�����黹ͼ��        ****"<< endl;
            cout<< "*****    4�����鿴ͼ��        ****"<< endl;
            cout<< "*****    0�����˳���¼        ****" << endl;
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
				cout << "�Ƿ�ȷ�ϲ�����        (Y/y)  or (N/n)" << endl;
				char yn;
				do
				{
					cin >> yn;
				} while (!(yn == 'Y' || yn == 'y' || yn == 'N' || yn == 'n'));
				if (yn == 'Y' || yn == 'y')
				{
					cout << "�����ɹ���" << endl;
					S_Save_file();
					B_Save_file();
				}
			}
			S_clear();
			B_clear();
		}
		cout << "δ�ҵ�������Ϣ��" << endl;
	}
	return true;
}

//�����ʼ������
void management::login_init()
{
	system("cls");
                cout<<"                            ������������������������������������������������������������������������������\n";
                cout<<"                            ��>>>>>>>>>��ӭ����ͼ�����ϵͳ<<<<<<<<��\n";
                cout<<"                            ������������������������������������������������������������������������������\n";
                cout<<"                            ��             1.����Ա                ��\n";
                cout<<"                            ������������������������������������������������������������������������������\n";
                cout<<"                            ��             2.ѧ��                  ��\n";
                cout<<"                            ������������������������������������������������������������������������������\n";
                cout<<"                            ��             3.�˳�ϵͳ              ��\n";
                cout<<"                            ������������������������������������������������������������������������������\n";
}

//������
int main()
{
	management xiangnan;
	//���ļ������ڣ����½��ļ�
	//���ѧ����Ϣ
	ofstream Student_Information("Student_Information.txt", ios::app);
	if (!Student_Information)
	{
		cerr << "�ļ�\"flight information.dat\"�޷���!\n";
		exit(1);
	}
	Student_Information.close();

	//���ͼ����Ϣ
	ofstream Book_Information("Book_Information.txt", ios::app);
	if (!Book_Information)
	{
		cerr << "�ļ�\"flight information.dat\"�޷���!\n";
		exit(1);
	}
	Book_Information.close();

	int dlry;										//��½��Ա
	while (1)
	{
		xiangnan.login_init();							//�����ʼ��
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
			cout << "�������������ѡ��!" << endl;
		}
	}
	return 0;
}
