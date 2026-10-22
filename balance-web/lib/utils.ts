import { toast } from "@/components/ui/toast"
import { format, parseISO } from "date-fns"
import { isRedirectError } from "next/dist/client/components/redirect-error"

export { cn } from "cn"

export async function executeAction(action : () => Promise<void>) {
    try {
        await action()
    } catch(e : any) {
        if(isRedirectError(e)) {
            console.log("Redirecting")
            throw e
        }

        const messages:string[] = JSON.parse(e.message)

        toast.add({
            title : "Message",
            description : messages
        })
    }
}

export function formatDateTime(value?: string) {
    if(value && value.length > 0) {
        var date = parseISO(value)
        return format(date, "yyyy-MM-dd HH:mm")
    }

    return "";
}