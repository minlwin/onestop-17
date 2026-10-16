import 'server-only'
import * as security from "@/lib/store/auth.store"
import { redirect } from 'next/navigation'
import { AuthResult } from '@/lib/types'

export async function publicRequest(
    path : string, 
    params?: RequestPrams,
    init?: RequestInit
) {

    const requestInit = getInitInfo(params, init)
    const requestUrl = url(path)
    const searchParam = requestInit.method === "GET" ? new URLSearchParams(params) : undefined
    const fetchUrl = searchParam ? `${requestUrl}?${searchParam.toString()}` : requestUrl
    
    const response = await fetch(fetchUrl, requestInit)
    
    if(!response.ok) {
        const errorMessages = await response.json()
        throw new Error(errorMessages)
    }

    return await response.json()
}

export async function secureRequest(
    path : string, 
    params?: RequestPrams,
    init? : RequestInit) {

    async function requestWithToken(token : string) {
        
        const requestInit = getInitInfo(params, init)
        const requestHeader:HeadersInit = {...requestInit.headers, "Authorization" : token}
        
        const requestUrl = url(path)
        const searchParam = requestInit.method === "GET" ? new URLSearchParams(params) : undefined
        const fetchUrl = searchParam ? `${requestUrl}?${searchParam.toString()}` : requestUrl
        
        return await fetch(fetchUrl, {...requestInit, headers: {...requestHeader}})
    }

    const accessToken = await security.getAccessToken()

    if(!accessToken) {
        const params = new URLSearchParams({message : "You have to sign in for this operation."})
        redirect(`/signin?${params.toString()}`)
    }

    let response = await requestWithToken(accessToken)

    if(response.status === 410) {
        const refreshToken = await security.getRefreshToken()

        if(refreshToken) {
            const requestInit = getInitInfo({token : refreshToken}, {method : "POST"})
            const refreshResponse = await fetch(url('anonymous/auth/refresh'), requestInit)

            if(!refreshResponse.ok) {
                const params = new URLSearchParams({message : "You session has been timeout. Please sign in again."})
                redirect(`/signin?${params.toString()}`)
            }

            const refreshResult:AuthResult = await refreshResponse.json()
            await security.setAuthResult(refreshResult)

            response = await requestWithToken(refreshResult.accessToken)
        }
    }

    if(response.status === 401) {
        const messages:string[] = await response.json()
        const params = new URLSearchParams({message : messages[0]})
        redirect(`/signin?${params.toString()}`)
    }

    if(!response.ok) {
        const errorMessages = await response.json()
        throw new Error(errorMessages)
    }

    return await response.json()
}

export type RequestPrams = {
    [name : string] : any
}

function url(path : string) {
    return `${process.env.BASE_API}/${path}`
}

function getInitInfo(params?: RequestPrams, init?: RequestInit) : RequestInit {
    const requestInit:RequestInit = init || {method : "GET"}

    if(requestInit.method === "POST" 
        || requestInit.method === "PUT") {
        
        if(params && hasFile(params)) {
            const formData = new FormData
            
            Object.keys(params).forEach(key => {
                formData.append(key, params[key])
            })
            requestInit.body = formData

        } else if(params) {
            requestInit.headers = {...requestInit.headers, "Content-Type" : "application/json"}
            requestInit.body = JSON.stringify(params)
        }
    } 

    return {...requestInit}
}

function hasFile(params: RequestPrams) {
    return Object.values(params).find(a => a instanceof File) !== undefined
}