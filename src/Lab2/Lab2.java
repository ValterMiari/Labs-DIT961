package Lab2;

import java.io.*;
import java.util.*;

public class Lab2 {
	public static String pureMain(String[] commands) {
		// TODO: declaration of two priority queues
		PriorityQueue<Bid> sell_pq = new PriorityQueue<>(new Sellers());
		PriorityQueue<Bid> buy_pq = new PriorityQueue<>(new Buyers());

		StringBuilder sb = new StringBuilder();

		for(int line_no=0;line_no<commands.length;line_no++){
			String line = commands[line_no];
			if( line.equals("") )continue;

			String[] parts = line.split("\\s+");
			if( parts.length != 3 && parts.length != 4)
				throw new RuntimeException("line " + line_no + ": " + parts.length + " words");
			String name = parts[0];
			if( name.charAt(0) == '\0' )
				throw new RuntimeException("line " + line_no + ": invalid name");
			String action = parts[1];
			int price;
			try {
				price = Integer.parseInt(parts[2]);
			} catch(NumberFormatException e){
				throw new RuntimeException(
						"line " + line_no + ": invalid price");
			}

			if( action.equals("K") ) {
				// TODO: add new buy bid
				buy_pq.add(new Bid(name, price));
			} else if( action.equals("S") ) {
				// TODO: add new sell bid
				sell_pq.add(new Bid(name, price));
			} else if( action.equals("NK") ){
				// TODO: update existing buy bid. use parts[3].
				buy_pq.change(new Bid(name, price), new Bid(name, Integer.parseInt(parts[3])));
			} else if( action.equals("NS") ){
				// TODO: update existing sell bid. use parts[3].
				sell_pq.change(new Bid(name, price), new Bid(name, Integer.parseInt(parts[3])));
			} else {
				throw new RuntimeException(
						"line " + line_no + ": invalid action");
			}

			if( sell_pq.size() == 0 || buy_pq.size() == 0 )continue;
			
			// TODO:
			// compare the bids of highest priority from each of
			// each priority queues.
			// if the lowest seller price is lower than or equal to
			// the highest buyer price, then remove one bid from
			// each priority queue and add a description of the
			// transaction to the output.
			Bid buyer = buy_pq.minimum();
			Bid seller = sell_pq.minimum();
			if (buyer.getBid() >= seller.getBid()) {
				buy_pq.deleteMinimum();
				sell_pq.deleteMinimum();
				sb.append(seller.getName() + " sold to " + buyer.getName() +
						" for the price of: " + seller.getBid() + "\n");
			}
		}

		sb.append("Order book:\n");

		sb.append("Lab2.Sellers: ");
		// TODO: print remaining sellers.
		//       can remove from priority queue until it is empty.
		sb.append(sell_pq.sequentialOrder() + "\n");

		sb.append("Lab2.Lab2.Buyers: ");
		// TODO: print remaining buyers
		//       can remove from priority queue until it is empty.
		sb.append(buy_pq.sequentialOrder() + "\n");
		/* for (int i = 0; i < 5; i++)
			System.out.println(sell_pq.deleteMinimum().toString());
		System.out.println("------------------");
		for (int i = 0; i < 3; i++)
			System.out.println(buy_pq.deleteMinimum().toString()); */

		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		final BufferedReader actions;
		if( args.length != 1 ){
			actions = new BufferedReader(new InputStreamReader(System.in));
		} else {
			actions = new BufferedReader(new FileReader(args[0]));
		}

		List<String> lines = new LinkedList<String>();
		while(true){
			String line = actions.readLine();
			if( line == null)break;
			lines.add(line);
		}
		actions.close();

		System.out.println(pureMain(lines.toArray(new String[lines.size()])));
	}
}
