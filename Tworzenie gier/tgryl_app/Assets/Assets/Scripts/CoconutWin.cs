using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[RequireComponent(typeof(AudioSource))]

public class CoconutWin : MonoBehaviour
{
    public static int targets = 0;
    public static bool haveWon = false;
    public AudioClip winSound;
    public GameObject cellPrefab;
    
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
    	Debug.Log("Coconut Win = " + targets);
    	Debug.Log("haveWon = " + haveWon.ToString());
       if( targets == 3 && haveWon == false) {
            Debug.Log("WINNNNNNNNNNNNNNNNNN");
            targets = 0;
            GetComponent<AudioSource>().PlayOneShot(winSound);
            GameObject winCell = transform.Find("powerCell").gameObject;
            winCell.transform.Translate(-1,0,0);
            Instantiate(cellPrefab, winCell.transform.position, transform.rotation);
            Destroy(winCell);
            haveWon = true;
       }
    }
}
