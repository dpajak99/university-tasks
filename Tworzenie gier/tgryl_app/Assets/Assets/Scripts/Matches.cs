using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Matches : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
 
    void OnTriggerEnter(Collider col){
	if(col.gameObject.tag == "Player"){
	    col.gameObject.SendMessage("MatchPickup");
	    Destroy(gameObject);
	}
    }
}
