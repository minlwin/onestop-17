'use client'

import FormsInput from "@/components/forms/forms-input"
import { Button } from "@/components/ui/button"
import { ResetPasswordForm, resetPasswordSchema } from "@/lib/types"
import { executeAction } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { Key } from "lucide-react"
import { useForm } from "react-hook-form"
import * as passwordService from "@/lib/service/action/password-recovery.action"

export default function ResetPasswordComponent() {

    const form = useForm({
        resolver: zodResolver(resetPasswordSchema),
        defaultValues: {
            optCode: '',
            password: ''
        }
    })

    function resetPassword(form : ResetPasswordForm) {
        executeAction(async () => {
            await passwordService.resetPassword(form)
        })
    }

    return (
        <form onSubmit={form.handleSubmit(resetPassword)} className="space-y-4">
            <FormsInput control={form.control} name="optCode" label="Secret Code" />
            <FormsInput control={form.control} name="password" label="Password" type="password" />

            <Button type="submit">
                <Key /> Reset Password
            </Button>
        </form>
    )
}