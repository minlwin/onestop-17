'use client'

import FormsInput from "@/components/forms/forms-input"
import { Button } from "@/components/ui/button"
import { ForgotPasswordForm, forgotPasswordSchema } from "@/lib/types/anonymous/auth.schema"
import { executeAction } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { Send } from "lucide-react"
import { useForm } from "react-hook-form"
import * as passwordRecoveryService from "@/lib/service/action/password-recovery.action"

export default function ForgotPasswordComponent() {
    const form = useForm({
        resolver: zodResolver(forgotPasswordSchema),
        defaultValues: {
            email: ""
        }
    })

    function sendRequest(form : ForgotPasswordForm) {
        executeAction(async () => {
            await passwordRecoveryService.sendRequest(form)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(sendRequest)} className="space-y-4">
            <FormsInput control={form.control} name="email" label="Email" type="email" />

            <Button type="submit">
                <Send /> Send Request
            </Button>
        </form>
    )
}