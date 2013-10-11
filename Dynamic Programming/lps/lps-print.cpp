#include<stdio.h>
#include<string.h>
 
int max (int x, int y) { return (x > y)? x : y; }

int L[1000][1000];
int P[1000][1000];
 
int lps(char *str)
{
   int n = strlen(str);
   int i, j, cl;
   //int L[n][n];  // Create a table to store results of subproblems
 
 
   // Strings of length 1 are palindrome of lentgh 1
   for (i = 0; i < n; i++){
      L[i][i] = 1;
      P[i][i] = 1;
   }
 
  // cl is the length of substring
    for (cl=2; cl<=n; cl++)
    {
        for (i=0; i<n-cl+1; i++)
        {
            j = i+cl-1;
            if (str[i] == str[j] && cl == 2){
               L[i][j] = 2;
               P[i][j] = 2;
            }else if (str[i] == str[j]){
               L[i][j] = L[i+1][j-1] + 2;
               P[i][j] = 3;
            }else{
               L[i][j] = max(L[i][j-1], L[i+1][j]);
               if(L[i][j-1]>L[i+1][j]){
                    P[i][j]=4;
               }else{
                    P[i][j]=5;
               }
            }
        }
    }
 
    return L[0][n-1];
}
 
void print_lps(char *seq, int i, int j)
{
	switch(P[i][j]){
	case 1:
		printf("%c",seq[i]);
		return;
	case 2:
		printf("%c%c",seq[i],seq[j]);
		return;
	case 3:
		printf("%c",seq[i]);
		print_lps(seq,i+1,j-1);
		printf("%c",seq[j]);
		return;
	case 4:
		print_lps(seq,i,j-1);
		return;
	case 5:
		print_lps(seq,i+1,j);
		return;
	}
}

int main()
{
    char seq[] = "ACGTGTCAAAATCG";
    int n = strlen(seq);
    printf ("%s\n",seq);
    printf ("The length of the LPS is %d\n", lps(seq));
    print_lps(seq,0,13);
    printf("\n");
    return 0;
}

