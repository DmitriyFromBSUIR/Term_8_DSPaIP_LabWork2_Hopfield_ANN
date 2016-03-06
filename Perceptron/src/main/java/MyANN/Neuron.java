/**
 * 
 */
package MyANN;

/**
 * @author Emperor
 * 17.02.2016
 * 
 * McCulloch-Pits Model of Neuron (Warren McCulloch and Walter Pits, 1940)
 */
public class Neuron {
	private int _weight;
	private int _axon;
	private int _dendrite;
	private int _connectingLinkStrength;
	
	Neuron(){
		_weight = 0;
		_axon = 0;
		_dendrite = 0;
		_connectingLinkStrength = 0;
	}
	
	Neuron(int weight, int dendrite, int axon){
		_weight = weight;
		_axon = axon;
		_dendrite = dendrite;
		_connectingLinkStrength = 0;
	}
	
	Neuron(int weight, int dendrite, int axon, int connectingLinkStrength){
		_weight = weight;
		_axon = axon;
		_dendrite = dendrite;
		_connectingLinkStrength = connectingLinkStrength;
	}
	
	public int getWeight(){
		return _weight;
	}
	
	public void setWeight(int newNeuronWeight){
		_weight = newNeuronWeight;
	}
	
	public int getAxon(){
		return _axon;
	}
	
	public void setAxon(int newNeuronAxon){
		_axon = newNeuronAxon;
	}
	
	public int getDendrite(){
		return _dendrite;
	}
	
	public void setDendrite(int newNeuronDendrite){
		_dendrite = newNeuronDendrite;
	}
	
	public int getConnectingLinkStrength(){
		return _connectingLinkStrength;
	}
	
	public void setConnectingLinkStrength(int newNeuronConnectingLinkStrength){
		_connectingLinkStrength = newNeuronConnectingLinkStrength;
	}
	
	public boolean equals(final Neuron neuron) {
		if( (_weight == neuron.getWeight()) && (_dendrite == neuron.getDendrite()) && (_axon == neuron.getAxon()) ) 
			return true;
		else
			return false;
	}
}
