using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
using UnityEngine.SceneManagement;
[RequireComponent (typeof (AudioSource))]

public class MainMenuBtns : MonoBehaviour, IPointerUpHandler, IPointerDownHandler, IPointerEnterHandler, IPointerExitHandler
{
    public string levelToLoad;
    public Sprite normalTexture;
    public Sprite rollOverTexture;
    public AudioClip beep;
    public bool quitButton = false;

    public void OnPointerEnter(PointerEventData eventData) {
        GetComponent<Image>().sprite = rollOverTexture;
    }

    public void OnPointerExit(PointerEventData eventData) {
        GetComponent<Image>().sprite=normalTexture;
    }

    public void OnPointerUp(PointerEventData eventData) {
        if(quitButton) {
        #if UNITY_EDITOR
            UnityEditor.EditorApplication.isPlaying = false;
        #else
            Application.Quit();
        #endif
        } else {
            GetComponent<AudioSource>().PlayOneShot(beep);
            SceneManager.LoadScene(levelToLoad);
        }
    }

    public void OnPointerDown(PointerEventData eventData) {

    }
    // Update is called once per frame
    void Update()
    {
        
    }
}
