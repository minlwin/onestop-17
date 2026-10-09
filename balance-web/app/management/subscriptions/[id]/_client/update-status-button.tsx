'use client'

import FormsSelect from "@/components/forms/forms-select"
import FormsTextarea from "@/components/forms/forms-textarea"
import { Button } from "@/components/ui/button"
import { Dialog, DialogContent, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { SubscriptionStatusForm, subscriptionStatusSchema } from "@/lib/types"
import { zodResolver } from "@hookform/resolvers/zod"
import { Shield, ShieldCheck } from "lucide-react"
import { useState } from "react"
import { useForm } from "react-hook-form"
import * as action from "@/lib/service/action/management/subscription.action"
import { executeAction } from "@/lib/utils"

export default function UpdateStatusButton({id} : {id : string}) {
    const [show, setShow] = useState(false)
    const form = useForm({
        resolver : zodResolver(subscriptionStatusSchema),
        defaultValues: {
            status: "",
            remark: ""
        }
    })

    function update(updateForm : SubscriptionStatusForm) {
        executeAction(async () => {
            action.updateStatus(id, updateForm)
        })
        form.reset()
        setShow(false)
    }

    return (
        <Dialog open={show} onOpenChange={setShow}>
            <DialogTrigger render={
                <Button>
                    <ShieldCheck /> Update Status
                </Button>
            } />

            <DialogContent>
                
                <DialogHeader>
                    <DialogTitle className={'flex gap-2 items-center'}> 
                        <ShieldCheck size={20} />
                        Update Status
                    </DialogTitle>
                </DialogHeader>

                <form onSubmit={form.handleSubmit(update)} className="space-y-4">
                    <FormsSelect control={form.control} name="status" label="Status" options={[
                        {label : "Select Status", value : ""},
                        {label : "Approve", value : "Approved"},
                        {label : "Reject", value : "Rejected"},
                    ]} />

                    <FormsTextarea control={form.control} name="remark" label="Remark" />
                    <DialogFooter>
                        <Button type="submit">
                            <Shield /> Update
                        </Button>
                    </DialogFooter>
                </form>
            </DialogContent>
        </Dialog>
    )
}