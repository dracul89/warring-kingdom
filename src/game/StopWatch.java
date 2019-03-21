package game;

public final class StopWatch implements I_StopWatch{
	
	private long start;
	
	private StopWatch(){
		this.start = System.currentTimeMillis();
	}

	public static StopWatch start(){
		return new StopWatch();
	}
	@Override
	public int elapsed() {
		// TODO Auto-generated method stub
		return (int) (System.currentTimeMillis() - start);
	}
	@Override
	public void reset(){
		this.start = System.currentTimeMillis();
	}
	
}
