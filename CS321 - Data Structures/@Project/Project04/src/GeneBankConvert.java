/**
 * CS321 - B Tree Project
 * This class is for converting long value to String and string value to long.
 * @author Ahram, Max, Ella
 *
 */
public class GeneBankConvert 
{
	private long key = 0;

	/**
	 * Long -> String
	 * @param sequence
	 * @param seqLength
	 * @return
	 */
	public String convertLongToString(long sequence, int seqLength)
	{
		String s = " ";
		long temp;

		for(int i = 1; i <= seqLength; i++)
		{
			temp = (sequence &3L << (seqLength -i) * 2);
			temp = temp >> (seqLength - i) * 2;

			if(temp == 0L)
			{
				s = s + "a";
			}
			else if(temp == 1L)
			{
				s = s + "c";
			}
			else if(temp == 2L)
			{
				s = s + "g";
			}
			else if(temp == 3L)
			{
				s = s + "t";
			}
		}
		return s;
	}

	/**
	 * String -> Long
	 * @param sequence
	 * @return
	 */
	public long convertStringToLong(String sequence)
	{	
		for(int i = 0; i < sequence.length(); i++)
		{
			if(sequence.charAt(i) == 'a')
			{
				if(i == 0)
				{
					key = 0;
				}
				else
				{
					key = key << 2;
					key = key | 0; 

				}
			}

			if(sequence.charAt(i) == 'c')
			{
				if(i == 0)
				{
					key = 1;
				}
				else
				{
					key = key << 2;
					key = key | 1;
				}
			}

			if(sequence.charAt(i) == 'g')
			{
				if(i == 0)
				{
					key = 2;
				}
				else
				{
					key = key << 2;
					key = key | 2;
				}
			}

			if(sequence.charAt(i) == 't')
			{
				if(i == 0)
				{
					key = 3;
				}
				else
				{
					key = key << 2;
					key = key | 3;
				}
			}
		}
		return key;
	}
}