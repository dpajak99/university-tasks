using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerCollisions : MonoBehaviour {
    bool doorIsOpen = false;
    float doorTimer = 0.0f;
    public float doorOpenTime = 3.0f;
    public AudioClip doorOpenSound;
    public AudioClip doorShutSound;
    
    // Start is called before the first frame update
    void Start() {
        
    }

    // Update is called once per frame
    void Update() {
        
    }
    
    void OnControllerColliderHit(ControllerColliderHit hit){
        if(hit.gameObject.tag == "playerDoor" && doorIsOpen == false){
            OpenDoor(hit.gameObject);
        }
    }
    
    void OpenDoor(GameObject door){
        doorIsOpen = true;
        door.GetComponent<AudioSource>().PlayOneShot(doorOpenSound);
        door.transform.parent.GetComponent<Animation>().Play("dooropen");
    }
}
