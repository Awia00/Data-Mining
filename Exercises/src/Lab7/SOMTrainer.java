package Lab7;

import java.util.Vector;

/**
 *
 * @author  alanter
 */
public class SOMTrainer implements Runnable {
	// These constants can be changed to play with the learning algorithm
	private static final double START_LEARNING_RATE = 0.07;
	private static final int	NUM_ITERATIONS = 500;
	
	// These two depend on the size of the lattice, so are set later
	private double LATTICE_RADIUS;
	private double TIME_CONSTANT;
	private LatticeRenderer renderer;
	private SOMLattice lattice;
	private Vector inputs;
	private static boolean running;
	private Thread runner;
	
	/** Creates a new instance of SOMTrainer */
	public SOMTrainer() {
		running = false;
	}
	
	
		
	// Train the given lattice based on a vector of input vectors
	public void setTraining(SOMLattice latToTrain, Vector in,
							LatticeRenderer latticeRenderer)
	{
		lattice = latToTrain;
		inputs = in;
		renderer = latticeRenderer;
        LATTICE_RADIUS = Math.max(lattice.getHeight(), lattice.getWidth())/2;
        TIME_CONSTANT = NUM_ITERATIONS/Math.log(LATTICE_RADIUS);
	}
	
	public void start() {
		if (lattice != null) {
			runner = new Thread(this);
			runner.setPriority(Thread.MIN_PRIORITY);
			running = true;
			runner.start();
		}
	}
	
	/***
	 * The run method is used to train/constuct the SOM.
	 */
	public void run() {
		//Initialize constants (e.g. t etc. you will need for calculations

        double learningRate = START_LEARNING_RATE;
        double neighbourhoodDistance = LATTICE_RADIUS;
		
		for(int iteration = 0;iteration < NUM_ITERATIONS && running;iteration++)
		{
            neighbourhoodDistance = LATTICE_RADIUS*Math.exp(-(double)iteration/TIME_CONSTANT);
			for (int i=0; i<inputs.size(); i++) {
				SOMVector currentInput = (SOMVector)inputs.elementAt(i);
				SOMNode bmu = lattice.getBMU(currentInput);

                for (int i1 = 0; i1 < lattice.getHeight(); i1++) {
                    for (int i2 = 0; i2 < lattice.getWidth(); i2++) {
                        SOMNode neighbour = lattice.getNode(i2,i1);
                        double distance = neighbour.distanceTo(bmu);
                        double widthSquare = neighbourhoodDistance * neighbourhoodDistance;
                        if(distance<widthSquare){
                            double distanceInfluence = Math.exp(-distance/(2*widthSquare));
                            neighbour.adjustWeights(currentInput, learningRate, distanceInfluence);
                        }
                    }
                }
            }
            learningRate = START_LEARNING_RATE*Math.exp(-(double)iteration/NUM_ITERATIONS);
			
			
			//Last thing to do, pass the updated lattice to the GUI to visualize it
			renderer.render(lattice, iteration);
		}
		
		//Used by the GUI
		running = false;
	}

	public void stop() {
		if (runner != null) {
			running = false;
			while (runner.isAlive()) {}
        }
	}
}
