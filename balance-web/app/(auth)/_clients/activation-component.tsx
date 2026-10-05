'use client'

import FormsInput from "@/components/forms/forms-input"
import { Button } from "@/components/ui/button"
import { ActivationForm, activationSchema } from "@/lib/types"
import { zodResolver } from "@hookform/resolvers/zod"
import { UserCheck } from "lucide-react"
import { useForm } from "react-hook-form"

export default function ActivationComponent() {

    const form = useForm({
        resolver: zodResolver(activationSchema),
        defaultValues: {
            optCode: '',
            password: ''
        }
    })

    function activateAccount(form : ActivationForm) {

    }

    return (
        <form onSubmit={form.handleSubmit(activateAccount)} className="space-y-4">
            <FormsInput control={form.control} name="optCode" label="Secret Code" />
            <FormsInput control={form.control} name="password" label="Password" type="password" />

            <Button type="submit">
                <UserCheck /> Activate Account
            </Button>
        </form>
    )
}