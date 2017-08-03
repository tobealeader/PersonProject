#include <stdio.h>
#include <string.h>
#include <stdlib.h>
struct dd		//文件结构体 
{
	struct dd* fa;		//父目录 
	struct dd* son[10];		//子目录 
	int l;				//子目录数量 
	char name[100];		//当前目录名称 
	char file[10][100];		//当前目录的各个文件 
	int file_l;		//文件数 
}*p;
void fd_load()		//文件加载 
{
	p=(struct dd*)malloc(sizeof(struct dd));
	strcpy(p->name,"dj");
	p->l=3;
	p->fa=NULL;
	for(int i=0;i<p->l;i++)
	{
		p->son[i]=(struct dd*)malloc(sizeof(struct dd));
		p->son[i]->l=0;
		p->son[i]->fa=p;
		if(i==0)
			strcpy(p->son[i]->name,"mulu0");
		else if(i==1)
			strcpy(p->son[i]->name,"mulu1");
		else if(i==2)
			strcpy(p->son[i]->name,"mulu2");
		strcpy(p->son[i]->file[0],"file");
		p->son[i]->file_l=1;
	}
	p->son[0]->l=1; 
	p->son[0]->son[0]=(struct dd*)malloc(sizeof(struct dd));
	p->son[0]->son[0]->l=0;
	p->son[0]->son[0]->fa=p->son[0];
	strcpy(p->son[0]->son[0]->name,"mulu00");
	strcpy(p->son[0]->son[0]->file[0],"file");
	p->son[0]->son[0]->file_l=1;
}
void fd_ls()		//下一级子目录的显示 
{
	for(int i=0;i<p->file_l;i++)
	{
		printf("%s ",p->file[i]);
	} 
	for(int i=0;i<p->l;i++)
	{
		printf("%s ",p->son[i]->name);
	} 
	printf("\n");
}
void fd_cd(int l,char str[])	//进入下一级的子目录 
{
	int i;
	if(l==5&&str[2]==' '&&str[3]=='.'&&str[4]=='.')
	{
		while(p->fa!=NULL)
		{
			p=p->fa;
		}
	}
	else if(l>2&&str[2]==' ')
	{
		char s[100];
		int k=0;
		for(i=3;i<l;i++)
		{
			s[k++]=str[i];
		}
		s[k]='\0';
		int a=p->l;
		for(i=0;i<p->l;i++)
		{
			if(strcmp(s,p->son[i]->name)==0)
			{
				p=p->son[i];
				break;
			}
		}
		if(i==a)
		{
			printf("No such mulu %s\n",s);
		}
	}
}
void fd_rm(int l,char str[])	//删除下一级的子目录 
{
	int i,j;
	if(l>2)
	{
		char s[100];
		int k=0;
		for(i=3;i<l;i++)
		{
			s[k++]=str[i];
		}
		s[k]='\0';
		int a=0;
		for(i=0;i<p->l;i++)
		{
			if(strcmp(s,p->son[i]->name)==0)
			{
				a=1;
				if(i==p->l-1)
				{
					p->son[i]=NULL;
					p->l--;
				}
				else
				{
					for(j=i;j<p->l-1;j++)
					{
						p->son[j]=p->son[j+1];
					}
					p->son[j]=NULL;
					p->l--;
				}
				break;
			}
		}
		for(i=0;i<p->file_l;i++)
		{
			if(strcmp(s,p->file[i])==0)
			{
				a=1;
				if(i==p->file_l-1)
				{
					p->file_l--;
				}
				else
				{
					for(j=i;j<p->file_l-1;j++)
					{
						strcpy(p->file[j],p->file[j+1]);
					}
					p->file_l--;
				}
				break;
			}
		}
		if(a==0)
		{
			printf("No such file\n");
		}
	}
}
int main()
{
	char str[1000]; //用户输入 
	int i;
	fd_load();
	while(1)
	{
		printf("[root@ubuntu: %s]# ",p->name);
		gets(str);
		int l=strlen(str); 
		if(l>=2&&str[0]=='l'&&str[1]=='s')
		{
			i=3;
			while(i<l&&str[i]==' ')
			{
				i++;
			}
			if(i<l)
				break;
			fd_ls();
		}
		else if(l>=2&&str[0]=='c'&&str[1]=='d')
		{
			fd_cd(l,str);
		}
		else if(l>=2&&str[0]=='r'&&str[1]=='m')
		{
			fd_rm(l,str);
		}
		else
		{
			printf("No such Command\n");
		}
	}
	return 0;
}
