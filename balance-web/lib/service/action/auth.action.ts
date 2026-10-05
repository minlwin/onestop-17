'use server'

import { ActivationForm, Role, SignInForm, SignUpForm } from "@/lib/types";
import * as client from "@/lib/service/client/auth.client"
import * as store from "@/lib/store/auth.store"
import { redirect } from "next/navigation";

export async function signIn(form: SignInForm) {
    const response = await client.signIn(form)
    await store.setAuthResult(response)
    redirect(getRoute(response.role))
}

export async function signUp(form: SignUpForm) {
    const response = await client.signUp(form)
    const params = new URLSearchParams
    params.append("message", response.result)
    redirect(`/activate?${params.toString()}`)
}

export async function activate(form : ActivationForm) {
    const response = await client.activate(form)
    const params = new URLSearchParams
    params.append("message", response.result)
    redirect(`/signin?${params.toString()}`)
}

function getRoute(role : Role) {
    if(role === 'Administrator' || role === 'Management') {
        return "/management"
    }

    return "/member"
}