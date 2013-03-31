package com.comp1008.kelvinkhookuokyao.TicTacToe;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;


	/**
	 * DrawView.java 
	 * @author Kelvin Khoo 12/02/2013
	 * Updated on 24/02/2013
	 */
	public class DrawView extends View implements OnTouchListener
	{
		private Paint cross = new Paint();
		private Paint circle = new Paint();
		private Paint background = new Paint();
		private Paint line = new Paint();
		private Paint score = new Paint();
		private Paint winline = new Paint();
		private Paint gridline = new Paint();
		private Path path = new Path();
		private int win=0;
		private int gameMode;
		private int width,height;
		private int player1win=0;
		private int player2win=0;
		private int comWin=0;
	
		private int sx,sy;
		private int[][] winCell = new int[3][3];
	
		private boolean circleTurn = true; //if true then is circle
		private int[][] cell = new int[3][3];
		
		
		public DrawView(Context context, int gameMode)
		{
			super(context);
			width=this.getWidth();
			height=this.getHeight();
			
			this.gameMode = gameMode;
			setFocusable(true);
			setFocusableInTouchMode(true);
			
			for (int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					cell[i][j] = -3;
				}
			}
			
			for(int a=0;a<3;a++)
			{
				for(int b=0;b<3;b++)
					winCell[a][b]=0;
			}

			background.setColor(Color.rgb(255,255,204));
			background.setStyle(Style.FILL);
			
			cross.setColor(Color.RED);
			
			line.setColor(Color.rgb(68,0,0));
			
			winline.setStyle(Style.STROKE);
			winline.setStrokeWidth(12);
			winline.setColor(Color.RED);
			
			gridline.setStyle(Style.STROKE);
			gridline.setStrokeWidth(24);
			gridline.setColor(Color.rgb(68,0,0));
			
			score.setColor(Color.GRAY);
			score.setStyle(Style.FILL);
			score.setTextSize(32);
			
			cross.setStrokeWidth(14);
			cross.setColor(Color.BLUE);
			cross.setStyle(Style.STROKE);
			
			circle.setStrokeWidth(14);
			circle.setColor(Color.RED);
			circle.setAntiAlias(true);
			circle.setStyle(Style.STROKE);
			this.setOnTouchListener(this);
		}
	
	
		@Override
		public void onDraw(Canvas canvas)
		{
		    int height = canvas.getHeight();
			int width = canvas.getWidth();
			int[] coordinate = {1,3,5};
			canvas.drawRect(getLeft(), getTop(), getRight(), getBottom(), background);//draw background
			
			//draw column 1
			path.moveTo(width/3, height/15);
			path.lineTo(width/3, 14*height/15);
			path.close();
			canvas.drawPath(path, gridline);
			
			//draw column 2
			path.moveTo(2*width/3, height/15);
			path.lineTo(2*width/3, 14*height/15);
			path.close();
			canvas.drawPath(path, gridline);
			
			//draw row 1
			path.moveTo(width/15, height/3);
			path.lineTo(14*width/15, height/3);
			path.close();
			canvas.drawPath(path, gridline);
			
			//draw row 2
			path.moveTo(width/15,2*height/3);
			path.lineTo(14*width/15, 2*height/3);
			path.close();
			
			canvas.drawPath(path, gridline);
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					int x=coordinate[i];
					int y=coordinate[j];
					if(this.cell[i][j]==0)
						canvas.drawCircle(width/6*x,(height/6)*y, (getWidth()/3/2)-width/15, circle);
					else if(this.cell[i][j]==1)
					{
						canvas.drawLine(x*width/6-width/15,y*height/6-width/15, x*width/6+width/15,y*height/6+width/15, cross);
						canvas.drawLine(x*width/6+width/15,y*height/6-width/15, x*width/6-width/15,y*height/6+width/15, cross);
					}	
				}
			}
			if(checkRow()==3||checkColumn()==3||checkDiagonal()==3)
			{
				
				if(gameMode==0)
				{
				drawWinLine(canvas);

				player2win++;
				Toast.makeText(getContext(), "Player2 Win!", Toast.LENGTH_SHORT).show();
				}
				else
				{
					drawWinLine(canvas);
					comWin++;
					Toast.makeText(getContext(), "Com Win", Toast.LENGTH_SHORT).show();
				}
			}
			
			if(checkRow()==0||checkColumn()==0||checkDiagonal()==0)
			{
				drawWinLine(canvas);
				player1win++;
				Toast.makeText(getContext(), "Player1 Win!", Toast.LENGTH_SHORT).show();
			}
			
			if(isFull()&&win!=1)
			{
				Toast.makeText(getContext(), "Draw!", Toast.LENGTH_SHORT).show();
			}
			if (gameMode ==0)
			{
				canvas.drawText("P1WIN: "+player1win, getLeft()+getWidth()/24, getTop()+getHeight()/20, score);
				canvas.drawText("P2WIN: "+player2win, getLeft()+getWidth()/2+getWidth()/24, getTop()+getHeight()/20, score);
			}
			else
			{
				canvas.drawText("P1WIN: "+player1win, getLeft()+getWidth()/24, getTop()+getHeight()/20, score);
				canvas.drawText("COMWIN: "+comWin, getLeft()+getWidth()/2+getWidth()/24, getTop()+getHeight()/20, score);
			}
			
		}
		
		public void drawWinLine(Canvas canvas)
		{
			int a=0,b=0;
			int[] coordinate = {1,3,5};
			for(a=0;a<3;a++)
			{
				for(b=0;b<3;b++)
				{
					if(winCell[a][b]==1)
					{	
						int x = coordinate[a];
						int y = coordinate[b];
						path.moveTo(x*getWidth()/6,y*getHeight()/6);
					}
				}
			}
			
			for(a=0;a<3;a++)
				for(b=0;b<3;b++)
				{
					if(winCell[a][b]==1)
					{	
						int x = coordinate[a];
						int y = coordinate[b];
						path.lineTo(x*getWidth()/6,y*getHeight()/6);
					}
				}
			path.close();
			canvas.drawPath(path, winline);	
		}
		
		public void clearBoard()
		{
			for (int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					this.cell[i][j] = -3;
				}
			}
			path.reset();
			
			for(int a=0;a<3;a++)
			{
				for(int b=0;b<3;b++)
					winCell[a][b]=0;
			}
			win=0;
			this.invalidate();
		}
		public boolean isFull()
		{
			boolean isitfull=true;
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					if(cell[i][j]==-3)
						isitfull=false;
			return isitfull;
		}
		
		public int checkRow()
		{
			int sum=0;
			int j;
			for(int i=0;i<3;i++)
			{
				j=0;
				sum=cell[i][j];
				winCell[i][j]=1;
				for(j=1;j<3;j++)
				{
					winCell[i][j]=1;
					sum=sum+cell[i][j];
					
					
				}	
				if(sum==0||sum==3)
				{	
					win=1;
					return sum;
				}
				
				for(int b=0;b<3;b++)
					winCell[i][b]=0;		
			}	
			return sum;
		}
		
		public int checkColumn()
		{
			int sum=0;
			int i;
			for(int j=0;j<3;j++)
			{
				i=0;
				sum=cell[i][j];
				winCell[i][j]=1;
				for(i=1;i<3;i++)
				{
					winCell[i][j]=1;
					sum=sum+cell[i][j];
				}	
				if(sum==0||sum==3)
				{
					win=1;
					return sum;
				}
				for(int b=0;b<3;b++)
					winCell[b][j]=0;
			}
			return sum;
		}
		
		public int checkDiagonal()
		{
			int sum;
			sum = cell[0][0]+cell[1][1]+cell[2][2];
			
			if(sum==0||sum==3)
			{
				winCell[0][0]=1;
				winCell[1][1]=1;
				winCell[2][2]=1;
				win =1;
				return sum;	
			}
			sum = cell[0][2]+cell[1][1]+cell[2][0];
			if(sum==0||sum==3)
			{
				winCell[0][2]=1;
				winCell[1][1]=1;
				winCell[2][0]=1;
				win=1;
			}
			return sum;	
		}
		
		public boolean onTouch(View v, MotionEvent event)
		{   
			if(event.getAction() != MotionEvent.ACTION_DOWN)
				return true;
			
			sx = (int)event.getX();
			sy = (int)event.getY();
			if(isFull()||win==1)
			{
					clearBoard();
					return true;
			}
			
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
				{
					int []drawcoordinate = {1,2,3};
					int x=drawcoordinate[i];
					int y=drawcoordinate[j];
					if((sx<(getWidth()*x/3))&&sx>((x-1)*getWidth()/3)&&sy>((y-1)*getHeight()/3)&&(sy<(y*getHeight()/3))&&this.cell[i][j]==-3)
					{
						if(gameMode == 0)
							circleTurn=!circleTurn;//alternate the choice
						if(circleTurn==true)
						{
							this.cell[i][j]=0;
						}
						else
						{
							this.cell[i][j]=1;
						}
						if (checkRow() == 3 || checkColumn() == 3 || checkDiagonal() == 3)
							win=1;
						if (checkRow() == 0 || checkColumn() == 0 || checkDiagonal() ==0)
							win=1;
						if (gameMode ==1 && win!=1)
							getComputerMove();
						v.invalidate();
						return true;
					}
				}
			return true;
		}
		public void getComputerMove()
		{
			int x = (int) (Math.random()*3);
			int y = (int) (Math.random()*3);
			if(isFull())
				return;
			if(cell[x][y]==-3)
				cell[x][y]=1;
			else
				getComputerMove();
		}
	}


