#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
	int n;
	while(cin>>n&&n){
		if(n==1 || n==2){
			cout<<"Alice"<<endl;
			continue;
		}
		if(n==3){
			cout<<"Bob"<<endl;
			continue;
		}
		cout<<"Bob"<<endl;
	}
}

// try to break into two equal half