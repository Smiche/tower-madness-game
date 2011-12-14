/*
    Graphics implementation for J2ME
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
import inc.games.AbstractGraphic;
import inc.games.ClipArea;

import javax.microedition.lcdui.*;

class GraphicJ2ME implements AbstractGraphic
{
	Graphics m_Graphic;

	public GraphicJ2ME(Graphics g)
	{
		m_Graphic=g;
	}

	public void FillRect(int x, int y, int w, int h)
	{
		m_Graphic.fillRect(x,y,w,h);
	}

	public void DrawRect(int x, int y, int w, int h)
	{
		m_Graphic.drawRect(x,y,w,h);
	}

	public void SetColor(int rgb)
	{
		m_Graphic.setColor(rgb);
	}

	//clip area functionality

	public void SetClip(ClipArea area)
	{
	    m_Graphic.setClip(area.GetX(),area.GetY(),area.GetW(),area.GetH());
	}

}




