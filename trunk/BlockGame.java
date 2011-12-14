/*
    BlockGame for J2ME
    Copyright (C) 2003 Irene Cummings (i.n.c@ezrs.com)

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;
import javax.microedition.rms.*;

import java.lang.*;
import java.util.*;
import java.io.*;

import inc.games.*;

public class BlockGame extends MIDlet implements CommandListener
{
	private GameArea m_GameArea;
	private Alert m_HighScore;
	private TextBox m_WinnerName;
	private Display m_Display;
	private Command m_ExitCmd;
	private Command m_NewCmd;
	private Command m_SaveGame;
	private Command m_ResetCmd;
	private Command m_DoneCmd;

 	public BlockGame()
 	{
		m_Display=Display.getDisplay(this);
		m_HighScore=new Alert("High Scores");
		m_HighScore.setTimeout(Alert.FOREVER);

		m_GameArea=new GameArea(10,6);
		m_ExitCmd=new Command("enough!",Command.EXIT,3);
		m_NewCmd=new Command("new game",Command.OK,2);
		m_SaveGame=new Command("save score",Command.OK,1);
		m_ResetCmd=new Command("Reset",Command.OK,2);
		m_GameArea.addCommand(m_ExitCmd);
		m_GameArea.addCommand(m_NewCmd);
		m_GameArea.addCommand(m_SaveGame);
		m_GameArea.setCommandListener(this);
		if (m_GameArea.IsResetable())
		{
			m_GameArea.addCommand(m_ResetCmd);	
		}

		m_WinnerName=new TextBox("New HS - enter name:",null,6,TextField.ANY);
		m_DoneCmd=new Command("done",Command.OK,1);
		m_WinnerName.addCommand(m_DoneCmd);
		m_WinnerName.setCommandListener(this);
 	}
 	
 	public void startApp()
 	{
		//System.out.println("--------------Begin Midlet---------------");
		m_Display.setCurrent(m_GameArea);
 	}
 	
 	public void destroyApp(boolean conditional)
 	{
		//System.out.println("--------------END MIDLET----------------");
		m_GameArea.finalize();
		this.notifyDestroyed();
 	}
 	
 	public void pauseApp()
 	{
		//noop
 	}

	public void commandAction(Command cmd, Displayable screen)
	{
		if (screen==m_GameArea)
		{
			if (cmd==m_ExitCmd)
			{
				//will kill immediately when this called
				this.destroyApp(false);
			}
			else if (cmd==m_ResetCmd)
			{
				m_GameArea.ResetHighScores();
				m_GameArea.removeCommand(m_ResetCmd);
			}
			else
			{
				//display high scores
				String hsText=m_GameArea.GetHighScore();
				m_HighScore.setString(hsText);
				if (cmd==m_NewCmd)
				{
					//have to set up new game first or crashes
					m_GameArea.NewGame();		
				}
				else if (cmd==m_SaveGame)
				{
					//if good enough save it!
					int winner=m_GameArea.IsScoreGood();
					if (winner>0)
					{
						m_WinnerName.setString(null);
						m_Display.setCurrent(m_WinnerName);
					}
					else
					{
						m_Display.setCurrent(m_HighScore);
					}
				}
			}
		}
		else if (screen==m_WinnerName)
		{
			String nickName=((TextBox)screen).getString();
			m_GameArea.SaveScore(nickName);
			m_Display.setCurrent(m_HighScore,m_GameArea);
		}
	}
}

class GameArea extends Canvas implements AbstractCanvas
{
	private BlockGameBase m_BlockGame;
	private HighScore m_HighScores;

	//usr location on screen
	private int m_PosX=0;
	private int m_PosY=0;

	//public GameArea(int sizeBlock, int numbColours,Display display)
	public GameArea(int sizeBlock, int numbColours)
	{
		m_BlockGame=new BlockGameBase(this,this.getWidth(),this.getHeight(),sizeBlock,numbColours);
		m_HighScores=new HighScore();
	}

	public void finalize()
	{
		m_HighScores.finalize();
	}

	public void keyPressed(int code)
	{
		int action=this.getGameAction(code);
		switch (action)
		{
			case UP:
			     //System.out.println("key press UP");
			     if (m_PosY>=m_BlockGame.m_BlockSize)
			     {
				m_PosY-=m_BlockGame.m_BlockSize;
				this.RefreshBlkHighlights(m_PosX,m_PosY+m_BlockGame.m_BlockSize);
			     }
			     break;
			case DOWN:
			     //System.out.println("key press DOWN");
			     if (m_PosY<=getHeight()-2*m_BlockGame.m_BlockSize)
			     {
				m_PosY+=m_BlockGame.m_BlockSize;
				this.RefreshBlkHighlights(m_PosX,m_PosY-m_BlockGame.m_BlockSize);
			     }
			     break;
			case LEFT:
			     //System.out.println("key press LEFT");
			     if (m_PosX>=m_BlockGame.m_BlockSize)
			     {
				m_PosX-=m_BlockGame.m_BlockSize;
				this.RefreshBlkHighlights(m_PosX+m_BlockGame.m_BlockSize,m_PosY);
			     }
			     break;
			case RIGHT:
			     //System.out.println("key press RIGHT");
			     if (m_PosX<=getWidth()-2*m_BlockGame.m_BlockSize)
			     {
				m_PosX+=m_BlockGame.m_BlockSize;
				this.RefreshBlkHighlights(m_PosX-m_BlockGame.m_BlockSize,m_PosY);
			     }
			     break;
			case FIRE:
			     //System.out.println("key press FIRE");
			     m_BlockGame.Respond2Event(m_PosX,m_PosY);
		}
	}
	
	private void RefreshBlkHighlights(int oldX, int oldY)
	{
		m_BlockGame.m_ClipArea.AddClipArea(oldX,oldY,m_BlockGame.m_BlockSize,m_BlockGame.m_BlockSize);
		m_BlockGame.m_ClipArea.AddClipArea(m_PosX,m_PosY,m_BlockGame.m_BlockSize,m_BlockGame.m_BlockSize);
		this.Repaint();
		m_BlockGame.m_ClipArea.Reset();
	}

	public void NewGame()
	{
		int sizeBlock=m_BlockGame.m_BlockSize;
		int numbColours=m_BlockGame.m_ColourNo;
		m_BlockGame=new BlockGameBase(this,this.getWidth(),this.getHeight(),sizeBlock,numbColours);
	}

	public void paint(Graphics graphic)
	{
		m_BlockGame.Paint(new GraphicJ2ME(graphic));
		//Indicate to usr where they are now by a "white" rectangle
		//(can be where blocks no longer exist)
		graphic.setColor(200,200,200);
		graphic.drawRect(m_PosX,m_PosY,m_BlockGame.m_BlockSize,m_BlockGame.m_BlockSize);

	}

	public void Repaint()
	{
		super.repaint();
		serviceRepaints();
	}

	//thin wrappers around data base
	public void SaveScore(String name)
	{
		m_HighScores.SaveScore(name,m_BlockGame.GetHighScore());
	}

	public String GetHighScore()
	{
		return m_HighScores.GetHighScore(m_BlockGame.GetHighScore());
	}

	public int IsScoreGood()
	{
		return m_HighScores.IsScoreGood(m_BlockGame.GetHighScore());
	}

	public void ResetHighScores()
	{
		m_HighScores.ResetHighScores();
	}

	public boolean IsResetable()
	{
		return m_HighScores.IsResetable();
	}

}
	

class HighScore extends HighScoreBase implements RecordComparator
{
	private RecordStore m_HighScores;

	public HighScore()
	{
		try
		{
			m_HighScores=RecordStore.openRecordStore("hiScores",true);
		}
		catch (RecordStoreException e)
		{
			//no score saving for you!
			m_HighScores=null;
		}
	}

	public void finalize()
	{
		try
		{
			m_HighScores.closeRecordStore();
		}
		catch (RecordStoreException e)
		{
			//no store saving for you
		}
	}

	//Highscore Functions

	//returns wether usr has made the hs table
	//-1 too low, 0 equal, 1 good enough to add, 2 table not full (-2 error)
	public int IsScoreGood(int score)
	{
		int rc=-1;
		try
		{
			//create string from records
			RecordEnumeration records=m_HighScores.enumerateRecords(null,this,false);

			//get records sorted and if lowest one smaller replace it!
			int numb=records.numRecords();
			if (numb<3)
			{
				rc=2;
			}
			else
			{
				//lowest first so just try first one
				int id=records.nextRecordId();
				byte[] thisR=m_HighScores.getRecord(id);
				byte asByte[]=this.CreateRecord(score,"",0);
				int goodScore=this.compare(asByte,thisR);
				if (goodScore==RecordComparator.FOLLOWS)
				{
					rc=1;
				}
				else if (goodScore==RecordComparator.EQUIVALENT)
				{
					rc=0;
				}
			}

		}
		catch (RecordStoreException e)
		{
			rc=-2;
		}
		//System.out.println("Is score good="+rc);
		return rc;
	}

	//have the reset conditions been met?
	public boolean IsResetable()
	{
		//currently allow resets every X days, but this could change in the future
		//eg numb games played, only HS winners can reset, etc etc
		boolean rc=false;
		long interval=7*24*60*60*1000;	//one week
		long current=System.currentTimeMillis();
		try
		{
			long lastMod=m_HighScores.getLastModified();
			if (current>lastMod+interval)
			{
				rc=true;
			}
		}
		catch (RecordStoreException e)
		{
			//System.out.println("IsResetable could not access high score rs");
		}
		return rc;
	}


	public void ResetHighScores()
	{
		try
		{
			m_HighScores.closeRecordStore();
			RecordStore.deleteRecordStore("hiScores");
			m_HighScores=RecordStore.openRecordStore("hiScores",true);
		}
		catch (RecordStoreException e)
		{
			//no score saving for you!
			m_HighScores=null;
			//System.out.println("ResetHighScores failed");
		}
	}

	//add HS to database
	public void SaveScore(String nickName,int score)
	{
		int scoreEnum=this.IsScoreGood(score);
		if (scoreEnum>0)
		{
			long date=System.currentTimeMillis();

			byte asByte[]=this.CreateRecord(score,nickName,date);
			//System.out.println(new String(asByte));
			try
			{			
				//get records sorted and if lowest one smaller replace it!
				if (scoreEnum==2)
				{
					m_HighScores.addRecord(asByte,0,asByte.length);
				}
				else
				{
					RecordEnumeration records=m_HighScores.enumerateRecords(null,this,false);
					//lowest first
					int id=records.nextRecordId();
					m_HighScores.setRecord(id,asByte,0,asByte.length);
				}
			}
			catch (RecordStoreException e)
			{
				//System.out.println("SaveScore failed");
			}
		}
		else
		{
			//System.out.println("Trying to save too low score ");
		}
	}

	//return HS display string
	public String GetHighScore(int score)
	{
		String result=new String("Current:\n");
		result+=score+"\n"+"Previous:\n";

		try
		{
			//create string from records
			RecordEnumeration records=m_HighScores.enumerateRecords(null,this,false);
			while (records.hasNextElement())
			{
				int id=records.nextRecordId();
				byte[] thisR=m_HighScores.getRecord(id);
				int[] scoreA={0};
				StringBuffer nickName=new StringBuffer();
				long[] date={0};
				this.DecomposeRecord(thisR,scoreA,nickName,date);
				result+=scoreA[0];
				result+=" ";
				result+=nickName;
				result+=" ";
				StringBuffer dateStr=this.LongDateToString(date[0]);
				result+=dateStr;				
				result+="\n";
			}
		}
		catch (RecordStoreException e)
		{
			result+="...no idea";
		}
		return result;
	}


	public int compare(byte[] rec1, byte[] rec2)
	{
		//grab date and compare (less o/h than streams, eg DecomposeRecord)
		byte[] byte1=new byte[4];
		byte[] byte2=new byte[4];
		System.arraycopy(rec1,0,byte1,0,4);
		System.arraycopy(rec2,0,byte2,0,4);

		//ok to simply do this because int was not stored as string number!
		String asStr1=new String(byte1);
		String asStr2=new String(byte2);

		if (asStr1.compareTo(asStr2)==0)
		{
			//System.out.println("score compare=0");
			return EQUIVALENT;
		}
		else if (asStr1.compareTo(asStr2)>0)
		{
			//System.out.println("score compare>0");
			return FOLLOWS;
		}
		else
		{
			//System.out.println("score compare<0");
			return PRECEDES;
		}
	}
}









