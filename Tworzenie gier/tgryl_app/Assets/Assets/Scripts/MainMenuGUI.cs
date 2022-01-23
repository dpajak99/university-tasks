using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
[RequireComponent (typeof (AudioSource))]

public class MainMenuGUI : MonoBehaviour
{
    public AudioClip beep;
    public GUISkin menuSkin;
    public Rect menuArea;
    public Texture gameLogo;
    Rect playBtnRect;
    Rect instructionsBtnRect;
    Rect quitBtnRect;
    float buttonWidth = 200;
    float buttonHeight = 40;
    float space = 20;
    public bool adjustPosition;
    public bool adjustSize;
    float coefX = 1.0f;
    float coefY = 1.0f;
    string menuPage = "main";
    public Rect instructionsRect;
    // Start is called before the first frame update
    void Start()
    {
        playBtnRect = new Rect(50, 250, buttonWidth, buttonHeight);
        instructionsBtnRect = new Rect(50, 250 + buttonHeight+space, buttonWidth, buttonHeight);
        quitBtnRect = new Rect(50, 250 + (buttonHeight+space)*2, buttonWidth, buttonHeight);

        if(adjustSize) {
            coefX = Screen.width/1024.0f;
            coefY = Screen.height/768.0f;

            menuArea.width*=coefX;
            menuArea.height*=coefY;
        }

        playBtnRect = new Rect(50*coefX, 250*coefY, buttonWidth*coefX, buttonHeight*coefY);
        instructionsBtnRect = new Rect(50*coefX, (250+buttonHeight+space) * coefY, buttonWidth*coefX, buttonHeight*coefY);
        quitBtnRect = new Rect(50*coefX, (250+(buttonHeight+space)*2)* coefY, buttonWidth*coefX, buttonHeight*coefY);

        if(adjustPosition) {
            float w_2 = menuArea.width * 0.5f;
            float h_2 = menuArea.height * 0.5f;

            menuArea.x = (menuArea.x + w_2) * Screen.width/1024 - w_2;
            menuArea.y = (menuArea.y + h_2) * Screen.height/768 - h_2;
        }

        instructionsRect = new Rect(0, 250*coefY, 300*coefX, buttonHeight*3*coefY);

        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnGUI() {
        GUI.skin=menuSkin;
        GUI.BeginGroup(menuArea);
        GUI.DrawTexture(new Rect(0, 0, 300*coefX, 211*coefY), gameLogo);

        if(menuPage == "main") {
            if(GUI.Button(playBtnRect, "Play")) {
                Debug.Log("Naciśnięto Play");
                StartCoroutine("ButtonAction", "SampleScene");
            }
            if(GUI.Button(instructionsBtnRect, "Instructions")) {
                Debug.Log("Naciśnięto Instructions");
                menuPage = "instructions";
                GetComponent<AudioSource>().PlayOneShot(beep);
            }
            if(GUI.Button(quitBtnRect, "Quit")) {
                Debug.Log("Naciśnięto Quit");
                StartCoroutine("ButtonAction", "quit");
            }
        } else if (menuPage == "instructions") {
            GUI.Label(instructionsBtnRect, "Obudziles sie na tejmniczej wyspie..." + "Znajdz sposob na zwrocenie na siebie uwagi, inaczej zostaniesz tu na zawsze!");
            if(GUI.Button(quitBtnRect, "Back")) {
                GetComponent<AudioSource>().PlayOneShot(beep);
                menuPage="main";
            }
        }
        GUI.EndGroup();
    }

    IEnumerator ButtonAction (string levelName) {
        GetComponent<AudioSource>().PlayOneShot(beep);
        yield return new WaitForSeconds(0.35f);
        if(levelName != "quit") {
            SceneManager.LoadScene(levelName);
        } else {
          #if UNITY_EDITOR  
          UnityEditor.EditorApplication.isPlaying=false;
          #else
          Application.Quit();
          #endif
        }
    }
}
