using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerCollisions : MonoBehaviour {
    GameObject currentDoor;
    
    void Start() {
        
    }

    void Update() {
       RaycastHit hit;
        if(Physics.Raycast (transform.position, transform.forward, out hit, 3)) {
            if(hit.collider.gameObject.tag=="playerDoor"){
                currentDoor = hit.collider.gameObject;
                currentDoor.SendMessage("DoorCheck");
            }
        }
    }
}
    
