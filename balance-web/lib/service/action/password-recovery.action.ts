'use server'

import { ForgotPasswordForm, ResetPasswordForm } from "@/lib/types";
import * as client from "@/lib/service/client/password-recovery.client"
import { redirect } from "next/navigation";

export async function sendRequest(form: ForgotPasswordForm) {
    const response = await client.sendRequest(form)
    const params = new URLSearchParams
    params.append("message", response.result)
    redirect(`/password/reset?${params.toString()}`)
}

export async function resetPassword(form : ResetPasswordForm) {
    const response = await client.resetPassword(form)
    const params = new URLSearchParams
    params.append("message", response.result)
    redirect(`/signin?${params.toString()}`)
}