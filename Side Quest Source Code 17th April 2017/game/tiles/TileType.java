package game.tiles;

public enum TileType
{
	
	Grass(0), Path(1);
	
	private int id;
	
	TileType(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
