using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PowerCell : MonoBehaviour
{
    public float rotationSpeed = 100.0f;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    
    // Update is called once per frame
    void Update()
    {
        transform.Rotate(new Vector3(0,rotationSpeed * Time.deltaTime ,0));
    }
    
    void OnTriggerEnter(Collider col) {
        if(col.gameObject.tag == "Player") {
            col.gameObject.SendMessage("CellPickup");
            Destroy(gameObject);
        }
    }
}
