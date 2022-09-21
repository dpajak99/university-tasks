package com.tarbus.services;

import com.tarbus.exceptions.BadRequestException;
import com.tarbus.exceptions.UnauthorizedException;
import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.request.LoginRequest;
import com.tarbus.payload.request.SignupRequest;
import com.tarbus.payload.response.JwtResponse;
import com.tarbus.payload.response.SignupResponse;
import com.tarbus.security.UserDetailsImpl;

public interface FileService {
  FileObject getFileById(String id);
}
