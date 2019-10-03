package SAMSUNG;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b17142
{
	
	public static int[] d_x = {-1,0,1,0};
	public static int[] d_y = {0,1,0,-1};
	
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringTokenizer st;
	public static int N,M;
	public static int[][] map,visited;
	public static ArrayList<Integer> v_x;
	public static ArrayList<Integer> v_y;
	public static int empty_cnt;
	public static int min_cnt=999999;
	
	public static void comb(int[] arr, int index, int n, int r, int target) 
	{
		if(r==0) 
		{
			int[] tmp = new int[M];
			for(int i = 0 ; i < index ; i++) {
				tmp[i]=arr[i];
			}
			
			Virus_BFS(tmp);
			
		}
		else if(target==n) 
		{
			return; 
		}
		else 
		{ 
			arr[index] = target; 
			comb(arr, index+1, n, r-1, target+1); 
			comb(arr, index, n, r, target+1); 
			
		} 
		
	}

	public static void Virus_BFS(int[] arr) 
	{
		Queue<Integer> X = new LinkedList<Integer>();
		Queue<Integer> Y = new LinkedList<Integer>();
		Queue<Integer> D = new LinkedList<Integer>();
		
		int out_x;
		int out_y;
		int out_d;
		
		visited = new int[N][N];
		int e_cnt = empty_cnt;
		
		for(int i = 0 ; i < M ; i++) 
		{
			int x = v_x.get(arr[i]);
			int y = v_y.get(arr[i]);
			
			visited[x][y]=1;
			
			X.offer(x);
			Y.offer(y);
			D.offer(0);
		}
				
		while(!X.isEmpty()) 
		{
			//System.out.println(e_cnt);
			out_x = X.poll();
			out_y = Y.poll();
			out_d = D.poll();
			//System.out.println(out_d);
			
			for(int i = 0 ; i < 4 ; i++) 
			{
				int _x = out_x+d_x[i];
				int _y = out_y+d_y[i];
				
				if(_x >=0 && _y >=0 && _x < N && _y < N) 
				{
					if(visited[_x][_y]!=1 && map[_x][_y]!=1)
					{
						visited[_x][_y]=1;
						X.offer(_x);
						Y.offer(_y);
						D.offer(out_d+1);
						
						if(map[_x][_y]==0)e_cnt-=1;
						
						if(e_cnt==0) {
							//System.out.println(out_d);
							min_cnt=Math.min(min_cnt, out_d+1);
							return;
						}
					}
				}
			}
		}
	}

	
	public static void main(String[] args) throws IOException 
	{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		v_x = new ArrayList<>();
		v_y = new ArrayList<>();
		empty_cnt = 0;
		
		for(int i = 0 ; i < N ; i++) 
		{
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0 ; j < N ; j++) 
			{
				
				int num = Integer.parseInt(st.nextToken());
				map[i][j]=num;
				
				if(num==0) 
				{
					empty_cnt+=1;
				}
				else if(num==2)
				{
					v_x.add(i);
					v_y.add(j);			
				}
			}
		}
		
		int[] arr = new int[v_x.size()];
		
		if(empty_cnt!=0) 
		{
			comb(arr,0,v_x.size(),M,0);
			if(min_cnt==999999)min_cnt=-1;
			bw.write(min_cnt+"");
		}
		else {
			bw.write(0+"");
		}
		
		bw.close();
		
	}

}
