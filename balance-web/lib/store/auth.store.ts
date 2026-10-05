import 'server-only'
import { AuthResult } from '../types';
import { cookies } from 'next/headers';
import { ResponseCookie } from 'next/dist/compiled/@edge-runtime/cookies';
import { env } from 'process';

const ACCESS_TOKEN = 'app.token.access'
const REFRESH_TOKEN = 'app.token.refresh'
const USER_INFO = 'app.user'

export async function setAuthResult(result : AuthResult) {
    const cookieStore = await cookies()
    const {accessToken, refreshToken, ... user} = result
    
    const cookieProps:Partial<ResponseCookie> = {
        httpOnly: true,
        sameSite: 'strict',
        secure: process.env.NODE_ENV === 'production',
        maxAge: 60 * 60 * 24
    } 

    cookieStore.set(ACCESS_TOKEN, accessToken, {...cookieProps})
    cookieStore.set(REFRESH_TOKEN, refreshToken, {...cookieProps})
    cookieStore.set(USER_INFO, JSON.stringify(user), {...cookieProps})
}

export async function getAccessToken() {
    const cookieStore = await cookies()
    return cookieStore.get(ACCESS_TOKEN)?.value
}

export async function getRefreshToken() {
    const cookieStore = await cookies()
    return cookieStore.get(REFRESH_TOKEN)?.value
}

export async function getUserInfo() {
    const cookieStore = await cookies()
    const value = cookieStore.get(USER_INFO)?.value

    if(value) {
        return JSON.parse(value)
    }
}

