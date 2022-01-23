using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TriggerWolf : MonoBehaviour
{
    Animator _animator;
    GameObject _player;
    // Start is called before the first frame update
    void Start()
    {
        _player = GameObject.FindGameObjectWithTag("Player");
        _animator = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerEnter(Collider other) {
        if(other.gameObject == _player) {
            _animator.SetBool("isNearPlayer", true);
        }
    }

    void OnTriggerExit(Collider other) {
        if(other.gameObject == _player) {
            _animator.SetBool("isNearPlayer", false);
        }
    }
}
